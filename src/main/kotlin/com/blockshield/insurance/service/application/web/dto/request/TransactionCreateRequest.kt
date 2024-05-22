package com.blockshield.insurance.service.application.web.dto.request

import com.blockshield.insurance.service.domain.transaction.entity.dto.AssetTransaction
import com.blockshield.insurance.service.domain.transaction.entity.dto.CreateTransactionCommand
import jakarta.validation.constraints.NotNull
import java.util.*

data class TransactionCreateRequest(
    val id: UUID = UUID.randomUUID(),
    @NotNull
    val hash: String,
    @NotNull
    val wallet: String,
    @NotNull
    val assetTransaction: AssetTransaction
) {
    fun toCreateTransactionCommand() = CreateTransactionCommand(id, hash, wallet, assetTransaction)
}
