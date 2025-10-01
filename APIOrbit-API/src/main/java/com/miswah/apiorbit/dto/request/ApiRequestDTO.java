package com.miswah.apiorbit.dto.request;


import com.miswah.apiorbit.enums.ApiStatus;
import com.miswah.apiorbit.enums.AuthTypeNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
}
