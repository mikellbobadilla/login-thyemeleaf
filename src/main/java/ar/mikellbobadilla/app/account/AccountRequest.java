package ar.mikellbobadilla.app.account;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRequest {
    @NotBlank(message = "Username must be required")
    private String username;
    @NotBlank(message = "Password must be required")
    private String password;
    @NotBlank(message = "Please confirm password")
    private String confirmPassword;
}
