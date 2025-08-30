package com.miswah.apiorbit.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiDefinitionResponseDto {

    private Long id;

    private String urlPath;

    private String description;

    private Long projectId;
}
