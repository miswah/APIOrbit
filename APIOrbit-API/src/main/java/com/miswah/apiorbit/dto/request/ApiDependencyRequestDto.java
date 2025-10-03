package com.miswah.apiorbit.dto.request;


import com.miswah.apiorbit.enums.RelationTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiDependencyRequestDto {

        private UUID sourceApiVersionId;

        private UUID  targetApiVersionId;

        private RelationTypes relationTypes;
}
