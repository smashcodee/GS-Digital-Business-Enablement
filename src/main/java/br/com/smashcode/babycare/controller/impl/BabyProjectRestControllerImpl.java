package br.com.smashcode.babycare.controller.impl;

import br.com.smashcode.babycare.controller.IBabyProjectRestController;
import br.com.smashcode.babycare.request.BabyProjectRequest;
import br.com.smashcode.babycare.response.BabyProjectResponse;
import br.com.smashcode.babycare.service.IBabyProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/baby/project")
@Slf4j
public class BabyProjectRestControllerImpl implements IBabyProjectRestController {
    @Autowired
    private IBabyProjectService service;

    @Override
    @PostMapping("/create")
    public ResponseEntity<BabyProjectResponse> createBabyProject(BabyProjectRequest request, String accountId) {
        var originalUID = UUID.fromString(accountId);
        var response = service.createBabyProject(request, originalUID);
        log.info("[ ** POST ** /baby/project/create ]: 201 - Created.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<BabyProjectResponse> updateBabyProject(BabyProjectRequest request, String projectId) {
        var originalUID = UUID.fromString(projectId);
        var response = service.updateBabyProject(originalUID, request);
        log.info("[ ** PUT ** /baby/project/update?projectId=[projectId] ]: 200 - Ok.");
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/me/list")
    public ResponseEntity<List<BabyProjectResponse>> listAllAccountsBabyProject(String accountId) {
        var originalUID = UUID.fromString(accountId);
        var response = service.listAllAccountsBabyProject(originalUID);
        log.info("[ ** GET ** /baby/project/me/list?accountId=[accountId] ]: 200 - Ok.");
        return ResponseEntity.ok(response);
    }

    /* ADMIN Role */
    @Override
    @GetMapping("/all/list")
    public ResponseEntity<List<BabyProjectResponse>> listAllBabyProjects() {
        var response = service.listAllBabyProjects();
        log.info("[ ** GET ** /baby/project/all/list ]: 200 - Ok.");
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBabyProject(String projectId) {
        var originalUID = UUID.fromString(projectId);
        service.deleteBabyProject(originalUID);
        log.info("[ ** DELETE ** /baby/project/delete?projectId=[projectId] ]: 204 - No content.");
        return ResponseEntity.noContent().build();
    }
}
