package com.blockshield.insurance.service.domain.asset.mapper

import com.blockshield.insurance.service.domain.asset.entity.Asset
import com.blockshield.insurance.service.domain.asset.entity.dto.AssetDto
import com.blockshield.insurance.service.domain.common.Mapper

class AssetDtoMapper : Mapper<Asset, AssetDto> {

    override fun mapFrom(input: Asset, auxiliary: AssetDto?): AssetDto = AssetDto(
        id = input.id,
        name = input.name!!,
        description = input.description!!,
        initial = input.initial!!,
        rating = input.rating!!,
        active = input.active!!,
        currentSupply = input.currentSupply,
        totalSupply = input.totalSupply!!,
        price = input.price!!,
        createdAt = input.createdAt,
        updatedAt = input.updatedAt
    )
}
