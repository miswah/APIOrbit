package com.miswah.apiorbit.dto.request;

import java.util.UUID;

public record DocumentationRequestDTO(UUID id, UUID apiId, String text) {
}
