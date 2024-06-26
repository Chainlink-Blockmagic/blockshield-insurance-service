package com.blockshield.insurance.service.application.web.dto.response

import com.blockshield.insurance.service.domain.transaction.entity.Transaction
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

data class AssetByWalletResponse(
    val id: UUID,
    val symbol: String,
    val quantity: Int,
    val amount: BigDecimal,
    val transactionHash: String,
) : Serializable {
    companion object {
        fun from(transaction: Transaction) = AssetByWalletResponse(
            id = transaction.assetTransaction!!.id,
            symbol = transaction.assetTransaction!!.symbol,
            quantity = transaction.assetTransaction!!.quantity,
            amount = transaction.assetTransaction!!.getAmount(),
            transactionHash = transaction.hash!!
        )
    }
}
