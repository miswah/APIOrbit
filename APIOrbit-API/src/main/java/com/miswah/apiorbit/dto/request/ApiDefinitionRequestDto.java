package com.miswah.apiorbit.dto.request;

import com.miswah.apiorbit.enums.AuthTypeNames;
import com.miswah.apiorbit.enums.HttpMethods;
import com.miswah.apiorbit.validators.UrlPathValidator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiDefinitionRequestDto {

    @NotBlank(message = "Url path is mandatory")
    @Size(max = 100, message = "URL path must be less than 100 characters")
    @UrlPathValidator.ValidUrlPath
    private String urlPath;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "HTTP method is mandatory")
    private HttpMethods httpMethods;

    @NotNull(message = "Auth Type is mandatory")
    private AuthTypeNames authTypeNames;

    @NotNull(message = "Project id is mandatory")
    private UUID projectId;
}
