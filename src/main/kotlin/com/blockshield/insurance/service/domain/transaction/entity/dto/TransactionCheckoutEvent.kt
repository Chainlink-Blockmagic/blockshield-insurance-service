package com.blockshield.insurance.service.domain.transaction.entity.dto

import java.util.*

data class TransactionCheckoutEvent(
    val id: UUID = UUID.randomUUID(),
    val hash: String,
    val wallet: String,
    val assetTransaction: AssetTransaction
) {
    fun toCreateTransactionCommand() = CreateTransactionCommand(id, hash, wallet, assetTransaction)
}
