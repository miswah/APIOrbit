package com.miswah.apiorbit.dto.response;

import com.miswah.apiorbit.enums.HttpMethods;

public record ApiByMethodCountResponseDTO(HttpMethods httpMethod, Long count) {
}
