package com.miswah.apiorbit.service;

import com.miswah.apiorbit.model.ProjectModel;
import org.springframework.stereotype.Service;

@Service
public interface ProjectLookUpService {
    ProjectModel getProjectById(Long id);
}
