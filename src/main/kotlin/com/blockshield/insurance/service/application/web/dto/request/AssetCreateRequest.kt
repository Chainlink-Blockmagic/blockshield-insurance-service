package com.blockshield.insurance.service.application.web.dto.request

import com.blockshield.insurance.service.domain.asset.entity.dto.AssetDto
import com.blockshield.insurance.service.domain.asset.entity.dto.Price

data class AssetCreateRequest(
    val name: String,
    val initial: String,
    val rating: Double,
    val description: String,
    val active: Boolean,
    val totalSupply: Long,
    val price: Price
) {
    fun toAssetDto() = AssetDto(
        name = name,
        description = description,
        initial = initial,
        rating = rating,
        active = active,
        totalSupply = totalSupply,
        price = price,
    )
}
