package com.blockshield.insurance.service.application.web.dto.response

import java.io.Serializable

data class AssetActiveValidateResponse(
    val isSettled: Boolean
) : Serializable
