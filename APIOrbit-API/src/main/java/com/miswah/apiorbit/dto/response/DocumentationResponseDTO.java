package com.miswah.apiorbit.dto.response;

import java.util.UUID;

public record DocumentationResponseDTO(UUID apiId, UUID id, String text) {
}
