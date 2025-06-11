package com.miswah.apiorbit.dto.request;

import com.miswah.apiorbit.enums.HttpMethods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MockApiRequestDto {

    public Long apiVersionId;
    public Long apiDefinitionId;
    public HttpMethods httpMethods;
    public Long projectId;
    public int delay;
}
