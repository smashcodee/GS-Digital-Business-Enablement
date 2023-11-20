package br.com.smashcode.babycare.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BabyProjectRequest {
    @NotBlank(message = "o nome do bebê é obrigatório.")
    private String babyName;
    private String genre;

    public BabyProjectRequest() { super(); }
}
