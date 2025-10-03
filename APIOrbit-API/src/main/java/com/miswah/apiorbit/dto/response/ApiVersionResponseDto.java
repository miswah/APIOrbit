package com.miswah.apiorbit.dto.response;

import com.miswah.apiorbit.enums.ApiStatus;
import com.miswah.apiorbit.model.ApiDependencyModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiVersionResponseDto {
    private UUID id;
    private float version;
    private UUID apiDefinitionId;
    private ApiStatus apiStatus;

    private String schemaRequest;

    private String schemaResponse;
    private ApiDependencyModel apiDependencyModel;
}
