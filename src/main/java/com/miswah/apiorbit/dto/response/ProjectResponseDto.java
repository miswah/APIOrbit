package com.miswah.apiorbit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {

    private Long id;
    private String name;
    private String description;
}
