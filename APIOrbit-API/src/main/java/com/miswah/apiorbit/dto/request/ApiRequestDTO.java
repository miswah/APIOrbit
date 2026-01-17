package com.miswah.apiorbit.dto.request;


import com.miswah.apiorbit.enums.AuthTypeNames;
import com.miswah.apiorbit.enums.HttpMethods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRequestDTO {

    private String name;
    private String description;
    private String category;
    private String tags;
    private String urlBase;
    private double version;
    private AuthTypeNames authType;
    private String documentationUrl;
    private String mockUrl;
    private String instructions;
    private HttpMethods httpMethod;
}
