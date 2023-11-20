package br.com.smashcode.babycare.service;

import br.com.smashcode.babycare.request.BabyProjectRequest;
import br.com.smashcode.babycare.response.BabyProjectResponse;

import java.util.List;
import java.util.UUID;

public interface IBabyProjectService {
    /**
     * create project
     * update project
     * show my projects
     * show all projects [admin role]
     * delete project
     * */

    BabyProjectResponse createBabyProject(BabyProjectRequest request, UUID accountId);
    BabyProjectResponse updateBabyProject(UUID babyProjectId, BabyProjectRequest request);
    List<BabyProjectResponse> listAllAccountsBabyProject(UUID accountId);
    List<BabyProjectResponse> listAllBabyProjects();
    void deleteBabyProject(UUID babyProjectId);
}
