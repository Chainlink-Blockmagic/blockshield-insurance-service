package com.blockshield.insurance.service.resources.asset

import com.blockshield.insurance.service.domain.asset.entity.Asset
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface AssetRepository : MongoRepository<Asset, UUID> {

    fun findByActiveIsTrue(pageable: Pageable): List<Asset>

}
