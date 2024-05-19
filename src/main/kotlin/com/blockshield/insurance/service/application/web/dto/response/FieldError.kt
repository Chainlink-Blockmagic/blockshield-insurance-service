package com.blockshield.insurance.service.application.web.dto.response


data class FieldError(
    var `field`: String? = null,
    var errorCode: String? = null
)
