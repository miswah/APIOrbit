package com.miswah.apiorbit.dto.response;

import java.util.UUID;

public record MockApiResponseDTO(UUID id, long delay, String schemaRequest, String schemaResponse) {
}
