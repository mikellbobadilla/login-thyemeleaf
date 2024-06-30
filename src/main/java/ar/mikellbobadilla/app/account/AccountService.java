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
}
