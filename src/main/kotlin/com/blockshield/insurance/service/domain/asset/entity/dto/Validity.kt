package com.blockshield.insurance.service.domain.asset.entity.dto

import java.time.OffsetDateTime

data class Validity(
    val from: OffsetDateTime,
    val until: OffsetDateTime
)
