package com.miswah.apiorbit.service.imp;

import com.miswah.apiorbit.dto.request.ProjectRequestDto;
import com.miswah.apiorbit.dto.response.ProjectResponseDto;
import com.miswah.apiorbit.model.ProjectModel;
import com.miswah.apiorbit.repository.ProjectRepository;
import com.miswah.apiorbit.repository.UserRepository;
import com.miswah.apiorbit.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    @Autowired
    public ProjectServiceImp(ProjectRepository projectRepository, UserRepository userRepository){
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<ProjectResponseDto> getAllProjects() {
        List<ProjectModel> projects = projectRepository.findAll();
        return convertToResponseDtoList(projects);
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {
        Optional<ProjectModel> project = projectRepository.findById(id);

        if(project.isEmpty()){
            throw new RuntimeException("No project found with that id");
        }
        return convertToResponseDto(project.get());
    }

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto ProjectRequestDto) {
        ProjectModel projectModel = convertToEntity(ProjectRequestDto);
        ProjectModel savedProject = projectRepository.save(projectModel);
        return convertToResponseDto(savedProject);
    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto) {
        ProjectModel project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));

        project.setName(projectRequestDto.getName());

        project.setDescription(projectRequestDto.getDescription());

        ProjectModel updatedProject = projectRepository.save(project);
        return convertToResponseDto(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        Optional<ProjectModel> project = projectRepository.findById(id);
        if(project.isEmpty()){
            throw new RuntimeException("No project found with that id");
        }

        projectRepository.delete(project.get());
    }


    private ProjectRequestDto convertToDTO(ProjectModel project) {
        ProjectRequestDto ProjectRequestDto = new ProjectRequestDto();
        ProjectRequestDto.setId(project.getId());
        ProjectRequestDto.setName(project.getName());
        ProjectRequestDto.setDescription(project.getDescription());
        ProjectRequestDto.setOwnerId(project.getUser_id().getId());
        ProjectRequestDto.setEmail(project.getUser_id().getEmail());
        return ProjectRequestDto;
    }

    private ProjectModel convertToEntity(ProjectRequestDto ProjectRequestDto) {
        ProjectModel project = new ProjectModel();
        project.setName(ProjectRequestDto.getName());
        project.setDescription(ProjectRequestDto.getDescription());
        project.setUser_id(userRepository.findByEmail(ProjectRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email " + ProjectRequestDto.getEmail())));
        return project;
    }

    private ProjectResponseDto convertToResponseDto(ProjectModel projectModel) {
        ProjectResponseDto projectResponseDto = new ProjectResponseDto();
        projectResponseDto.setId(projectModel.getId());
        projectResponseDto.setName(projectModel.getName());
        projectResponseDto.setDescription(projectModel.getDescription());
        return projectResponseDto;
    }

    private List<ProjectResponseDto> convertToResponseDtoList(List<ProjectModel> projects) {
        return projects.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
}
