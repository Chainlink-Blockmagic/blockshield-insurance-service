package com.blockshield.insurance.service.domain.asset.entity.dto

import java.time.OffsetDateTime
import java.util.*

data class AssetDto(
    val id: UUID? = null,
    val name: String,
    val initial: String,
    val rating: Double,
    val description: String,
    val active: Boolean,
    val totalSupply: Long,
    val currentSupply: Long? = null,
    val price: Price,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null
) {
    fun isSettled() = this.active
}
