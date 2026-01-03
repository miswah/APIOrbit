package com.miswah.apiorbit.controller;

import com.miswah.apiorbit.dto.request.ProjectRequestDto;
import com.miswah.apiorbit.dto.response.ProjectResponseDto;
import com.miswah.apiorbit.model.ProjectModel;
import com.miswah.apiorbit.service.ProjectService;
import com.miswah.apiorbit.utils.ActivityLog;
import com.miswah.apiorbit.utils.Loggable;
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
    @ActivityLog(action="GET_ALL_PROJECT", module="PROJECT")
    @Loggable
    public ResponseEntity<List<ProjectResponseDto>> getAllProject(){
        List<ProjectResponseDto> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    @ActivityLog(action="GET_PROJECT_BY_ID", module="PROJECT", target="#result?.id")
    @Loggable
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable UUID id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PostMapping
    @ActivityLog(action="CREATE_PROJECT", module="PROJECT")
    @Loggable
    public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto projectDTO, Principal principal) {
        ProjectResponseDto createdProject = projectService.createProject(projectDTO, principal.getName());
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ActivityLog(action="DELETE_PROJECT_BY_ID", module="PROJECT", target="#result?.id")
    @Loggable
    public ResponseEntity<UUID> deleteProject(@PathVariable UUID id){
        projectService.deleteProject(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ActivityLog(action="UPDATE_PROJECT_BY_ID", module="PROJECT", target="#result?.id")
    @Loggable
    public ResponseEntity<ProjectResponseDto> updateProject(@Valid @RequestBody ProjectRequestDto projectRequestDto, @PathVariable UUID id){
        ProjectResponseDto projectResponseDto = projectService.updateProject(id, projectRequestDto);
        return ResponseEntity.ok(projectResponseDto);
    }
}
