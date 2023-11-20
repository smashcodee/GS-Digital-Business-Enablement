package br.com.smashcode.babycare.controller;

import br.com.smashcode.babycare.request.BabyProjectRequest;
import br.com.smashcode.babycare.response.BabyProjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IBabyProjectRestController {
    ResponseEntity<BabyProjectResponse> createBabyProject(
            @RequestBody BabyProjectRequest request,
            @RequestParam(name = "accountId") String accountId
    );

    ResponseEntity<BabyProjectResponse> updateBabyProject(
            @RequestBody BabyProjectRequest request,
            @RequestParam(name = "projectId") String projectId
    );

    ResponseEntity<List<BabyProjectResponse>> listAllAccountsBabyProject(
            @RequestParam(name = "accountId") String accountId
    );

    /* ADMIN Role */
    ResponseEntity<List<BabyProjectResponse>> listAllBabyProjects();

    ResponseEntity<Void> deleteBabyProject(
            @RequestParam(name = "projectId") String projectId
    );
}
