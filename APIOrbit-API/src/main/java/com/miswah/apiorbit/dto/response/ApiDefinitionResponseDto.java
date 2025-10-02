package com.miswah.apiorbit.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiDefinitionResponseDto {

    private UUID id;

    private String urlPath;

    private String description;

    private UUID projectId;
}
