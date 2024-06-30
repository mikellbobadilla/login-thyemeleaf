package ar.mikellbobadilla.app.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    private final AccountService service;

    public RegisterController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/register")
    String registerPage() throws AccountException {
        service.createAccount("sting", "holamundo", Role.ADMIN);
        return "register";
    }
}
