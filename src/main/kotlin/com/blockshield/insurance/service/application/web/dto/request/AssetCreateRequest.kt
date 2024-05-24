package com.blockshield.insurance.service.application.web.dto.request

import com.blockshield.insurance.service.domain.asset.entity.dto.AssetDto
import com.blockshield.insurance.service.domain.asset.entity.dto.Price
import jakarta.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class AssetCreateRequest(
    @NotNull
    val name: String,
    @NotNull
    val symbol: String,
    @NotNull
    val rating: String,
    @NotNull
    val description: String,
    @NotNull
    val observation: String,
    @NotNull
    val tokenizationPlatform: String,
    @NotNull
    val insuranceTokenAddress: String,
    val active: Boolean = false,
    @NotNull
    val totalSupply: Long,
    @NotNull
    val price: Price,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val dueDate: LocalDate,
) {
    fun toAssetDto() = AssetDto(
        name = name,
        description = description,
        observation = observation,
        symbol = symbol.uppercase(),
        tokenizationPlatform = tokenizationPlatform.uppercase(),
        rating = rating,
        active = active,
        totalSupply = totalSupply,
        price = price,
        dueDate = dueDate,
        insuranceTokenAddress = insuranceTokenAddress
    )
}
