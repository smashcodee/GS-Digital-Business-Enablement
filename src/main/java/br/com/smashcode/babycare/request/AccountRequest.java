package br.com.smashcode.babycare.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountRequest {
    @Email(message = "Por favor insira um email válido.")
    @NotBlank(message = "O email é obrigatório.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String password;

    @NotBlank(message = "O username é obrigatório.")
    @Size(min = 2, message = "O username deve ter no mínimo 2 caracteres.")
    private String username;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 4, message = "O nome deve ter no mínimo 4 caracteres.")
    private String fullName;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @PastOrPresent
    private LocalDate birthDate;

    @NotBlank(message = "O tipo sanguíneo é obrigatório.")
    private String bloodType;

    public AccountRequest() { super(); }
}
