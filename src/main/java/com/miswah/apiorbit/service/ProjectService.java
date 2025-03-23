package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.request.ProjectRequestDto;
import com.miswah.apiorbit.dto.response.ProjectResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<ProjectResponseDto> getAllProjects();
    ProjectResponseDto getProjectById(Long id);
    ProjectResponseDto createProject(ProjectRequestDto ProjectRequestDto);
    ProjectResponseDto updateProject(Long id, ProjectRequestDto ProjectRequestDto);
    void deleteProject(Long id);
}
