package com.blockshield.insurance.service.application.web.dto.response

import java.io.Serializable

data class FieldError(
    var `field`: String? = null,
    var errorCode: String? = null
) : Serializable
