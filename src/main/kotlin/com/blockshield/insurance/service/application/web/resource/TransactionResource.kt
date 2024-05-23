package com.blockshield.insurance.service.application.web.resource

import com.blockshield.insurance.service.application.web.dto.request.TransactionCreateRequest
import com.blockshield.insurance.service.application.web.dto.response.TransactionCreatedResponse
import com.blockshield.insurance.service.application.web.dto.response.TransactionResponse
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

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping(
    value = ["/api/v1/transactions"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class TransactionResource(private val transactionService: TransactionService) {

    @GetMapping("/wallet/{wallet}")
    fun getTransactionsByWallet(
        @PageableDefault(
            size = 30,
            page = 0,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable,
        @PathVariable(name = "wallet") wallet: String
    ): ResponseEntity<List<TransactionResponse>> =
        transactionService.getByWallet(wallet, pageable)
            .map { TransactionResponse.from(it) }
            .let {
                ResponseEntity.ok(it)
            }

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createTransaction(
        @RequestBody @Valid createTransactionRequest: TransactionCreateRequest
    ): ResponseEntity<TransactionCreatedResponse> =
        transactionService.create(createTransactionRequest.toCreateTransactionCommand()).let {
            return ResponseEntity(TransactionCreatedResponse(it.toString()), HttpStatus.CREATED)
        }
}
