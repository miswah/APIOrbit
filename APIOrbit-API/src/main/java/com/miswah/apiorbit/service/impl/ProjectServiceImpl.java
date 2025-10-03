package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.request.ProjectRequestDto;
import com.miswah.apiorbit.dto.response.ProjectResponseDto;
import com.miswah.apiorbit.model.ProjectModel;
import com.miswah.apiorbit.repository.ProjectRepository;
import com.miswah.apiorbit.service.ProjectService;
import com.miswah.apiorbit.service.UserLookUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final UserLookUpService userLookUpService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserLookUpService userLookUpService){
        this.projectRepository = projectRepository;
        this.userLookUpService = userLookUpService;
    }


    @Override
    public List<ProjectResponseDto> getAllProjects() {
        List<ProjectModel> projects = projectRepository.findAll();
        return convertToResponseDtoList(projects);
    }

    @Override
    public ProjectResponseDto getProjectById(UUID id) {
        Optional<ProjectModel> project = projectRepository.findById(id);

        if(project.isEmpty()){
            throw new RuntimeException("No project found with that id");
        }
        return convertToResponseDto(project.get());
    }

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto ProjectRequestDto, String email) {
        ProjectModel projectModel = convertToEntity(ProjectRequestDto, email);
        ProjectModel savedProject = projectRepository.save(projectModel);
        return convertToResponseDto(savedProject);
    }

    @Override
    public ProjectResponseDto updateProject(UUID id, ProjectRequestDto projectRequestDto) {
        ProjectModel project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));

        project.setName(projectRequestDto.getName());

        project.setDescription(projectRequestDto.getDescription());

        ProjectModel updatedProject = projectRepository.save(project);
        return convertToResponseDto(updatedProject);
    }

    @Override
    public void deleteProject(UUID id) {
        Optional<ProjectModel> project = projectRepository.findById(id);
        if(project.isEmpty()){
            throw new RuntimeException("No project found with that id");
        }

        projectRepository.delete(project.get());
    }

    private ProjectModel convertToEntity(ProjectRequestDto dto, String email) {
        ProjectModel model = new ProjectModel();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setUser(userLookUpService.getUserByEmail(email));
        model.setBaseUrl(dto.getBaseUrl());
        return model;
    }

    private ProjectResponseDto convertToResponseDto(ProjectModel model) {
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        dto.setBaseUrl(model.getBaseUrl());
        return dto;
    }

    private List<ProjectResponseDto> convertToResponseDtoList(List<ProjectModel> projects) {
        return projects.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
}
