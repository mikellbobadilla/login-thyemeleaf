package ar.mikellbobadilla.app.account;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository repository;
    private final PasswordEncoder encoder;

    public AccountService(AccountRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public void createAccount(String username, String password, Role role) throws AccountException {
        boolean usernameExists = repository.existsByUsername(username);

        if (usernameExists) throw new AccountException("Account already exists");

        String passwordEncoded = encoder.encode(password);

        Account account = Account.builder()
                .username(username)
                .password(passwordEncoded)
                .role(role)
                .build();

        repository.save(account);
    }

    public void createAccount(AccountRequest request) throws AccountException {
        boolean isPasswordMatch = request.getPassword().equals(request.getConfirmPassword());
        boolean isUsernameExists = repository.existsByUsername(request.getUsername());
        if (!isPasswordMatch) throw new AccountException("Password miss match");
        if (isUsernameExists) throw new AccountException("Username exists");

        String passwordEncoded = encoder.encode(request.getPassword());

        Account newAccount = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoded)
                .role(Role.USER)
                .build();
        repository.save(newAccount);
    }
}
