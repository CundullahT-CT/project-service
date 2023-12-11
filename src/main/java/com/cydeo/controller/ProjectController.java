package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.wrapper.ResponseWrapper;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RolesAllowed("Manager")
    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createProject(@Valid @RequestBody ProjectDTO projectDTO) {

        ProjectDTO createdProject = projectService.create(projectDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.CREATED)
                        .message("Project is successfully created.")
                        .data(createdProject)
                        .build());

    }

    @RolesAllowed({"Admin", "Manager"})
    @GetMapping("/read/{projectCode}")
    public ResponseEntity<ResponseWrapper> getByProjectCode(@PathVariable("projectCode") String projectCode) {

        ProjectDTO foundProject = projectService.readByProjectCode(projectCode);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("Project is successfully retrieved.")
                        .data(foundProject)
                        .build());

    }

    @RolesAllowed("Manager")
    @GetMapping("/read/manager/{projectCode}")
    public ResponseEntity<ResponseWrapper> getManagerByProjectCode(@PathVariable("projectCode") String projectCode) {

        String foundManager = projectService.readManagerByProjectCode(projectCode);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("Manager information is successfully retrieved.")
                        .data(foundManager)
                        .build());

    }

    @RolesAllowed("Manager")
    @GetMapping("/read/all/details")
    public ResponseEntity<ResponseWrapper> getProjectsWithDetails() {

        List<ProjectDTO> foundProjects = projectService.readAllProjectsWithDetails();

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("Projects are successfully retrieved.")
                        .data(foundProjects)
                        .build());

    }

    @RolesAllowed("Admin")
    @GetMapping("/read/all/admin")
    public ResponseEntity<ResponseWrapper> adminGetProjects() {

        List<ProjectDTO> foundProjects = projectService.adminReadAllProjects();

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("Projects are successfully retrieved.")
                        .data(foundProjects)
                        .build());

    }

    @RolesAllowed("Manager")
    @GetMapping("/read/all/manager")
    public ResponseEntity<ResponseWrapper> managerGetProjects() {

        List<ProjectDTO> foundProjects = projectService.managerReadAllProjects();

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("Projects are successfully retrieved.")
                        .data(foundProjects)
                        .build());

    }

    @RolesAllowed("Admin")
    @GetMapping("/count/manager/{assignedManager}")
    ResponseEntity<ResponseWrapper> getCountByAssignedManager(@PathVariable("assignedManager") String assignedManager) {

        Integer projectCount = projectService.countByAssignedManager(assignedManager);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("Project count is retrieved.")
                        .data(projectCount)
                        .build());

    }

    @RolesAllowed({"Admin", "Manager"})
    @GetMapping("/check/{projectCode}")
    public ResponseEntity<ResponseWrapper> checkByProjectCode(@PathVariable("projectCode") String projectCode) {

        boolean result = projectService.checkByProjectCode(projectCode);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("Project exists.")
                        .data(result)
                        .build());

    }

    @RolesAllowed("Manager")
    @PutMapping("/update/{projectCode}")
    public ResponseEntity<ResponseWrapper> updateProject(@PathVariable("projectCode") String projectCode,
                                                         @Valid @RequestBody ProjectDTO projectDTO) {

        ProjectDTO updatedProject = projectService.update(projectCode, projectDTO);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("Project is successfully updated.")
                        .data(updatedProject)
                        .build());

    }

    @RolesAllowed("Manager")
    @PutMapping("/complete/{projectCode}")
    public ResponseEntity<ResponseWrapper> completeProject(@PathVariable("projectCode") String projectCode) {

        ProjectDTO completedProject = projectService.complete(projectCode);

        return ResponseEntity
                .ok(ResponseWrapper.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK)
                        .message("Project is successfully completed.")
                        .data(completedProject)
                        .build());

    }

    @RolesAllowed("Manager")
    @DeleteMapping("/delete/{projectCode}")
    public ResponseEntity<Void> deleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.delete(projectCode);
        return ResponseEntity.noContent().build();
    }

}
