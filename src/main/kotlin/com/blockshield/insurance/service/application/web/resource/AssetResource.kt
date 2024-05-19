package com.blockshield.insurance.service.application.web.resource

import com.blockshield.insurance.service.application.web.dto.request.AssetCreateRequest
import com.blockshield.insurance.service.application.web.dto.request.AssetUpdateRequest
import com.blockshield.insurance.service.application.web.dto.response.AssetCreatedResponse
import com.blockshield.insurance.service.domain.asset.AssetService
import com.blockshield.insurance.service.domain.asset.entity.dto.AssetDto
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(
    value = ["/api/v1/assets"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AssetResource(private val assetService: AssetService) {

    @GetMapping
    fun getAllAssets(
        @PageableDefault(
            size = 30,
            page = 0,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable
    ): ResponseEntity<List<AssetDto>> = ResponseEntity.ok(assetService.findAll(pageable))

    @GetMapping("/active-only")
    fun getAllActiveAssets(
        @PageableDefault(
            size = 30,
            page = 0,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable
    ): ResponseEntity<List<AssetDto>> = ResponseEntity.ok(assetService.findAll(pageable, onlyActive = true))

    @GetMapping("/{id}")
    fun getAssetById(@PathVariable(name = "id") id: UUID): ResponseEntity<AssetDto> =
        ResponseEntity.ok(assetService.get(id))

    @GetMapping("/wallet/{wallet}")
    fun getAssetsByWallet(
        @PageableDefault(
            size = 30,
            page = 0,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable,
        @PathVariable(name = "ncm") ncm: Long
    ): ResponseEntity<List<AssetDto>> =
        ResponseEntity.ok(emptyList())

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createAsset(@RequestBody @Valid assetCreateRequest: AssetCreateRequest): ResponseEntity<AssetCreatedResponse> =
        assetService.create(assetCreateRequest.toAssetDto()).let {
            return ResponseEntity(AssetCreatedResponse(it.toString()), HttpStatus.CREATED)
        }

    @PutMapping("/{id}")
    fun updateAsset(
        @PathVariable(name = "id") id: UUID,
        @RequestBody @Valid assetUpdateRequest: AssetUpdateRequest
    ): ResponseEntity<UUID> =
        assetService.update(id, assetUpdateRequest.toAssetDto()).let {
            return ResponseEntity.ok(id)
        }

    @PatchMapping("/{id}/inactivate")
    @ApiResponse(responseCode = "204")
    fun inactivateAsset(@PathVariable(name = "id") id: UUID): ResponseEntity<Unit> =
        assetService.inactivate(id).let {
            return ResponseEntity.noContent().build()
        }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteAsset(@PathVariable(name = "id") id: UUID): ResponseEntity<Unit> =
        assetService.delete(id).let {
            return ResponseEntity.noContent().build()
        }
}
