package com.miswah.apiorbit.dto.request;

import com.miswah.apiorbit.validators.JsonValidator;

import java.util.UUID;

public record MockApiAdminRequestDTO(UUID apiId, Integer delay, @JsonValidator.ValidJson String schemaRequest, @JsonValidator.ValidJson String schemaResponse) {
}
