package com.miswah.apiorbit.dto.response;

import org.springframework.http.HttpStatus;

public record ApiByMethodCountResponse(HttpStatus httpStatus, Long count) {
}
