package ar.mikellbobadilla.app.account;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class RegisterController {

    private final AccountService service;

    public RegisterController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/create")
    String registerPage(Model model) throws AccountException {
        model.addAttribute("accountRequest", new AccountRequest());
        return "register";
    }

    @PostMapping("/create")
    String createAccount(@Valid @ModelAttribute AccountRequest request, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) return "register";
            service.createAccount(request);
            return "redirect:/";
        } catch (AccountException exc) {
            result.rejectValue(null, "error.accountRequest", exc.getMessage());
            return "register";
        }
    }
}
