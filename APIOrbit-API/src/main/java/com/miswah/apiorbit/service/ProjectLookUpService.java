package com.miswah.apiorbit.service;

import com.miswah.apiorbit.model.ProjectModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ProjectLookUpService {
    ProjectModel getProjectById(UUID id);
}
