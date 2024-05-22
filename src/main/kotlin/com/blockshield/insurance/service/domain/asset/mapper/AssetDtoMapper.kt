package com.blockshield.insurance.service.domain.asset.mapper

import com.blockshield.insurance.service.domain.asset.entity.Asset
import com.blockshield.insurance.service.domain.asset.entity.dto.AssetDto
import com.blockshield.insurance.service.domain.common.Mapper

class AssetDtoMapper : Mapper<Asset, AssetDto> {

    override fun mapFrom(input: Asset, auxiliary: AssetDto?): AssetDto = AssetDto(
        id = input.id,
        name = input.name!!,
        description = input.description!!,
        observation = input.observation!!,
        symbol = input.symbol!!,
        rating = input.rating!!,
        active = input.active!!,
        tokenizationPlatform = input.tokenizationPlatform!!,
        remainingSupply = input.remainingSupply,
        totalSupply = input.totalSupply!!,
        totalValue = input.totalValue(),
        price = input.price!!,
        dueDate = input.dueDate!!,
        createdAt = input.createdAt,
        updatedAt = input.updatedAt,
    )
}
