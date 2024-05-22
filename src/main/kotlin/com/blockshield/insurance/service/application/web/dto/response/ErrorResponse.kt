package com.blockshield.insurance.service.application.web.dto.response

import java.io.Serializable

data class ErrorResponse(
    var httpStatus: Int? = null,
    var exception: String? = null,
    var message: String? = null,
    var fieldErrors: List<FieldError>? = null
) : Serializable
