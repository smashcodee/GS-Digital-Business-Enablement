package br.com.smashcode.babycare.response;

import br.com.smashcode.babycare.model.BabyProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BabyProjectResponse {
    private UUID codBabyProject;
    private String babyName;
    private String genre;

    public BabyProjectResponse(BabyProjectEntity en) {
        this.codBabyProject = en.getCodBabyProject();
        this.babyName = en.getBabyName();
        if(!en.getGenre().equals(null)) {
            this.genre = en.getGenre();
        }
    }
}
