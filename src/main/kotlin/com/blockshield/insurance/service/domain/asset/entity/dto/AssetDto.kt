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
    val price: Price,
    val dueDate: LocalDate,
    val insuranceTokenAddress: String? = "",
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null
) {
    var remainingSupply: Long? = null

    fun isSettled() = !this.active

    fun remainingSupply(value: Long) = this.apply { this.remainingSupply = value }

    fun getRemainingSupply() = this.remainingSupply ?: MIN_REMAINING_VALUE

    fun updateRemainingSupply(quantity: Int) = this.apply {
        this.remainingSupply = quantity + (remainingSupply ?: MIN_REMAINING_VALUE)
    }

    companion object {
        private const val MIN_REMAINING_VALUE = 0L
    }
}
