package com.miswah.apiorbit.service.impl;


import com.miswah.apiorbit.model.ProjectModel;
import com.miswah.apiorbit.repository.ProjectRepository;
import com.miswah.apiorbit.service.ProjectLookUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectLookUpServiceImpl implements ProjectLookUpService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectLookUpServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectModel getProjectById(UUID id) {
        return this.projectRepository.findById(id).orElseThrow();
    }
}
