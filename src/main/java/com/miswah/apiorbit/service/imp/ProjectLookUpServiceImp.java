package com.miswah.apiorbit.service.imp;


import com.miswah.apiorbit.model.ProjectModel;
import com.miswah.apiorbit.repository.ProjectRepository;
import com.miswah.apiorbit.service.ProjectLookUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectLookUpServiceImp implements ProjectLookUpService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectLookUpServiceImp(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectModel getProjectById(Long id) {
        return this.projectRepository.findById(id).orElseThrow();
    }
}
