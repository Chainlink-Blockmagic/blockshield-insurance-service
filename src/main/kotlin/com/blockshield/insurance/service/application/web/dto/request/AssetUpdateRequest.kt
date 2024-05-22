package com.blockshield.insurance.service.application.web.dto.request

import com.blockshield.insurance.service.domain.asset.entity.dto.AssetDto
import com.blockshield.insurance.service.domain.asset.entity.dto.Price
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class AssetUpdateRequest(
    val name: String,
    val symbol: String,
    val rating: String,
    val description: String,
    val observation: String,
    val tokenizationPlatform: String,
    val active: Boolean,
    val totalSupply: Long,
    val price: Price,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val dueDate: LocalDate,
) {
    fun toAssetDto() = AssetDto(
        name = name,
        description = description,
        observation = observation,
        symbol = symbol,
        tokenizationPlatform = tokenizationPlatform,
        rating = rating,
        active = active,
        totalSupply = totalSupply,
        price = price,
        dueDate = dueDate,
    )
}
