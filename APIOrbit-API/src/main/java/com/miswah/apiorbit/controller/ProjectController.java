package com.miswah.apiorbit.controller;

import com.miswah.apiorbit.dto.request.ProjectRequestDto;
import com.miswah.apiorbit.dto.response.ProjectResponseDto;
import com.miswah.apiorbit.model.ProjectModel;
import com.miswah.apiorbit.service.ProjectService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProject(){
        List<ProjectResponseDto> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable UUID id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto projectDTO, Principal principal) {
        ProjectResponseDto createdProject = projectService.createProject(projectDTO, principal.getName());
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteProject(@PathVariable UUID id){
        projectService.deleteProject(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@Valid @RequestBody ProjectRequestDto projectRequestDto, @PathVariable UUID id){
        ProjectResponseDto projectResponseDto = projectService.updateProject(id, projectRequestDto);
        return ResponseEntity.ok(projectResponseDto);
    }
}
