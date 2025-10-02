package com.miswah.apiorbit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {

    private UUID id;
    private String name;
    private String description;
}
