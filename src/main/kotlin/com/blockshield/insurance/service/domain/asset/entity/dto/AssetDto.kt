package com.blockshield.insurance.service.domain.asset.entity.dto

import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*

data class AssetDto(
    val id: UUID? = null,
    val name: String,
    val symbol: String,
    val rating: String,
    val description: String,
    val observation: String,
    val tokenizationPlatform: String,
    val active: Boolean,
    val totalSupply: Long,
    val totalValue: BigDecimal = BigDecimal.ZERO,
    val remainingSupply: Long? = null,
    val price: Price,
    val dueDate: LocalDate,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null
) {
    fun isSettled() = !this.active
}
