package com.miswah.apiorbit.dto.request;

import com.miswah.apiorbit.validators.UrlPathValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotBlank(message = "Base Url is mandatory")
    @Pattern(regexp = "^(https?:\\/\\/(?:www\\.)?|www\\.)[a-zA-Z0-9-]{2,256}\\.[a-z]{2,6}\\b", message = "Not a valid base url")
    @Size(max = 500, message = "Base url must be less than 500 characters")
    private String baseUrl;
}
