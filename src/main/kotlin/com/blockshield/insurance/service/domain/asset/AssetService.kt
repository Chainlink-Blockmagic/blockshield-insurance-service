package com.blockshield.insurance.service.domain.asset

import com.blockshield.insurance.service.domain.asset.entity.dto.AssetDto
import com.blockshield.insurance.service.domain.asset.mapper.AssetDtoMapper
import com.blockshield.insurance.service.domain.asset.mapper.AssetEntityMapper
import com.blockshield.insurance.service.domain.exception.NotFoundException
import com.blockshield.insurance.service.resources.asset.AssetRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class AssetService(private val assetRepository: AssetRepository) {
    private val entityMapper = AssetEntityMapper()
    private val dtoMapper = AssetDtoMapper()

    fun findAll(pageable: Pageable, onlyActive: Boolean = false): List<AssetDto> {
        val assets = if (onlyActive) {
            assetRepository.findByActiveIsTrue(pageable)
        } else {
            assetRepository.findAll(pageable)
        }

        return assets.map { asset -> dtoMapper.mapFrom(asset, ASSET_NULL) }.toList()
    }

    fun `get`(id: UUID): AssetDto = assetRepository.findById(id)
        .map { nfe -> dtoMapper.mapFrom(nfe, ASSET_NULL) }
        .orElseThrow { NotFoundException("Asset for $id does not exists!") }

    fun inactivate(id: UUID) = assetRepository.findById(id)
        .orElseThrow { NotFoundException("Asset for $id does not exists!") }
        .let { assetRepository.save(it.inactivate()) }


    fun create(assetDto: AssetDto): UUID {
        val asset = entityMapper.mapFrom(assetDto, ASSET_NULL)
        return assetRepository.save(asset).id!!
    }

    fun update(id: UUID, assetDto: AssetDto) {
        val asset = assetRepository.findById(id)
            .orElseThrow { NotFoundException("Asset for $id does not exists!") }

        entityMapper.mapFrom(assetDto, asset).run {
            assetRepository.save(this)
        }
    }

    fun delete(id: UUID) = assetRepository.deleteById(id)

    companion object {
        private val ASSET_NULL = null
    }
}
