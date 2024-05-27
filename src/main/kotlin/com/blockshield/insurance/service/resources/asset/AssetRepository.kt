package com.blockshield.insurance.service.resources.asset

import com.blockshield.insurance.service.domain.asset.entity.Asset
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface AssetRepository : MongoRepository<Asset, UUID> {

    @Query("{ 'insuranceTokenAddress' : {\$regex : ?0, \$options: 'i'}}")
    fun findByInsuranceTokenAddress(address: String): Optional<Asset>

    fun findBySymbol(symbol: String): Optional<Asset>

    fun findByActiveIsTrue(pageable: Pageable): List<Asset>
}
