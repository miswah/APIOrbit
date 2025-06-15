package com.miswah.apiorbit.dto.request;

import com.miswah.apiorbit.enums.ApiStatus;
import com.miswah.apiorbit.validators.JsonValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiVersionRequestDto {
    public float version;
    public Long apiDefinitionId;
    public ApiStatus apiStatus;

    @JsonValidator.ValidJson
    public String schemaRequest;

    @JsonValidator.ValidJson
    public String schemaResponse;
}
