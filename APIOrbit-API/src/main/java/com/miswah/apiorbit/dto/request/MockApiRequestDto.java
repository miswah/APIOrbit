package com.miswah.apiorbit.dto.request;

import com.miswah.apiorbit.enums.HttpMethods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MockApiRequestDto {

    public UUID apiVersionId;
    public UUID apiDefinitionId;
    public HttpMethods httpMethods;
    public UUID projectId;
    public int delay;
}
