package com.miswah.apiorbit.dto.response;

import com.miswah.apiorbit.enums.Roles;

import java.util.UUID;

public record UserResponseDTO(UUID uuid, String email, String name, Roles role) {
}
