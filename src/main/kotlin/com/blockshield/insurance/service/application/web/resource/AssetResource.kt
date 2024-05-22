package com.blockshield.insurance.service.application.web.resource

import com.blockshield.insurance.service.application.web.dto.request.AssetCreateRequest
import com.blockshield.insurance.service.application.web.dto.request.AssetUpdateRequest
import com.blockshield.insurance.service.application.web.dto.response.AssetActiveValidateResponse
import com.blockshield.insurance.service.application.web.dto.response.AssetByWalletResponse
import com.blockshield.insurance.service.application.web.dto.response.AssetCreatedResponse
import com.blockshield.insurance.service.domain.asset.AssetService
import com.blockshield.insurance.service.domain.asset.entity.dto.AssetDto
import com.blockshield.insurance.service.domain.transaction.TransactionService
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
class AssetResource(
    private val assetService: AssetService,
    private val transactionService: TransactionService
) {

    @GetMapping
    fun getAllAssets(
        @RequestParam(
            name = "onlyActive",
            required = false,
            defaultValue = "false"
        )
        onlyActive: Boolean,
        @PageableDefault(
            size = 30,
            page = 0,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable
    ): ResponseEntity<List<AssetDto>> = ResponseEntity.ok(assetService.findAll(pageable, onlyActive = onlyActive))

    @GetMapping("/{id}")
    fun getAssetById(@PathVariable(name = "id") id: UUID): ResponseEntity<AssetDto> =
        ResponseEntity.ok(assetService.get(id))

    @GetMapping("/{symbol}/settled")
    fun getAssetById(@PathVariable(name = "symbol") symbol: String): ResponseEntity<AssetActiveValidateResponse> =
        ResponseEntity.ok(
            AssetActiveValidateResponse(assetService.getBySymbol(symbol.uppercase()).isSettled())
        )

    @GetMapping("/wallet/{wallet}")
    fun getAssetsByWallet(
        @PageableDefault(
            size = 30,
            page = 0,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable,
        @PathVariable(name = "wallet") wallet: String
    ): ResponseEntity<List<AssetByWalletResponse>> =
        transactionService.getByWallet(wallet, pageable).map {
            AssetByWalletResponse.from(it)
        }.let {
            ResponseEntity.ok(it)
        }

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
