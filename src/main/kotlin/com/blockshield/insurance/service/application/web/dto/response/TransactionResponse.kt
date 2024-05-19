package com.blockshield.insurance.service.application.web.dto.response

import com.blockshield.insurance.service.domain.transaction.entity.Transaction
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

data class TransactionResponse(
    val id: UUID,
    val hash: String,
    val wallet: String,
    val assertTransaction: AssetTransactionResponse,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
) {
    companion object {
        fun from(transaction: Transaction) = TransactionResponse(
            id = transaction.id!!,
            hash = transaction.hash!!,
            wallet = transaction.wallet!!,
            assertTransaction = AssetTransactionResponse(
                id = transaction.assetTransaction!!.id,
                initial = transaction.assetTransaction!!.initial,
                quantity = transaction.assetTransaction!!.quantity,
                amount = transaction.assetTransaction!!.getAmount()
            ),
            createdAt = transaction.createdAt!!,
            updatedAt = transaction.updatedAt!!,
        )
    }
}

data class AssetTransactionResponse(
    val id: UUID,
    val initial: String,
    val quantity: Int,
    val amount: BigDecimal,
)
