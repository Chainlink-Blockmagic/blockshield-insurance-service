package com.blockshield.insurance.service.domain.asset.mapper

import com.blockshield.insurance.service.domain.asset.entity.Asset
import com.blockshield.insurance.service.domain.asset.entity.dto.AssetDto
import com.blockshield.insurance.service.domain.common.Mapper
import java.time.OffsetDateTime
import java.util.*

class AssetEntityMapper : Mapper<AssetDto, Asset> {

    override fun mapFrom(input: AssetDto, auxiliary: Asset?): Asset {
        val asset = auxiliary?.apply {
            this.id = input.id ?: this.id
            this.name = input.name
            this.description = input.description
            this.observation = input.observation
            this.symbol = input.symbol
            this.rating = input.rating
            this.active = input.active
            this.price = input.price
            this.remainingSupply = input.remainingSupply ?: this.remainingSupply
            this.tokenizationPlatform = input.tokenizationPlatform
            this.totalSupply = input.totalSupply
            this.dueDate = input.dueDate
            this.insuranceTokenAddress = input.insuranceTokenAddress
            this.createdAt = input.createdAt ?: this.createdAt
            this.updatedAt = input.updatedAt ?: this.updatedAt
        } ?: Asset().apply {
            this.id = UUID.randomUUID()
            this.name = input.name
            this.description = input.description
            this.observation = input.observation
            this.symbol = input.symbol
            this.rating = input.rating
            this.active = input.active
            this.price = input.price
            this.remainingSupply = input.remainingSupply ?: this.remainingSupply
            this.tokenizationPlatform = input.tokenizationPlatform
            this.totalSupply = input.totalSupply
            this.dueDate = input.dueDate
            this.tokenizationPlatform = input.tokenizationPlatform
            this.createdAt = OffsetDateTime.now()
            this.updatedAt = OffsetDateTime.now()
        }

        return asset
    }
}
