package com.miswah.apiorbit.dto.response;


import com.miswah.apiorbit.enums.AuthTypeNames;
import com.miswah.apiorbit.enums.HttpMethods;
import com.miswah.apiorbit.model.ProjectModel;
import com.miswah.apiorbit.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiDefinitionResponseDto {

    private UUID id;

    private String name;

    private String urlPath;

    private String description;

    private ProjectModel projectId;

    private String userEmail;

    private HttpMethods httpMethods;

    private AuthTypeNames authTypeNames;
}
