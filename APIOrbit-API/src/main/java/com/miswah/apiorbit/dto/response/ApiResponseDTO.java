package com.miswah.apiorbit.dto.response;


import com.miswah.apiorbit.enums.ApiStatus;
import com.miswah.apiorbit.enums.AuthTypeNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String tags;
    private String urlBase;
    private double version;
    private ApiStatus status;
    private AuthTypeNames authType;
    private String documentationUrl;
    private String mockUrl;
    private String createdBy;
    private String approvedBy;
    private Date updatedDate;
}
