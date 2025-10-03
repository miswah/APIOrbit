package com.miswah.apiorbit.dto.response;


import com.miswah.apiorbit.enums.ResourceStatus;
import com.miswah.apiorbit.enums.AuthTypeNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private String category;
    private String tags;
    private String urlBase;
    private double version;
    private ResourceStatus status;
    private AuthTypeNames authType;
    private String documentationUrl;
    private String mockUrl;
    private String createdBy;
    private String approvedBy;
    private Date updatedDate;
}
