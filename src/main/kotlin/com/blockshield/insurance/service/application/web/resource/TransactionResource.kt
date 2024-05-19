package com.blockshield.insurance.service.application.web.resource

import com.blockshield.insurance.service.application.web.dto.response.TransactionResponse
import com.blockshield.insurance.service.domain.transaction.TransactionService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}
