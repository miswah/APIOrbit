package com.miswah.apiorbit.dto.request;

import com.miswah.apiorbit.enums.ApiStatus;
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
    public String schemaRequest;
    public String schemaResponse;
}
