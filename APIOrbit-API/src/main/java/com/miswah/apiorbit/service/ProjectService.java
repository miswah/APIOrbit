package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.request.ProjectRequestDto;
import com.miswah.apiorbit.dto.response.ProjectResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProjectService {
    List<ProjectResponseDto> getAllProjects();
    ProjectResponseDto getProjectById(UUID id);
    ProjectResponseDto createProject(ProjectRequestDto ProjectRequestDto, String name);
    ProjectResponseDto updateProject(UUID id, ProjectRequestDto ProjectRequestDto);
    void deleteProject(UUID id);
}
