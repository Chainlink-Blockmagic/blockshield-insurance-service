package com.blockshield.insurance.service.domain.transaction.entity.dto

import com.blockshield.insurance.service.domain.asset.AssetService
import com.blockshield.insurance.service.domain.transaction.entity.Transaction
import java.util.*

data class CreateTransactionCommand(
    val id: UUID? = null,
    val hash: String,
    val wallet: String,
    val assetTransaction: AssetTransaction
) {
    fun toTransaction(assetService: AssetService): Transaction =
        assetService.get(assetTransaction.id)
            .let {
                Transaction()
                    .uuid(id)
                    .hash(hash)
                    .wallet(wallet)
                    .assetTransaction(
                        assetTransaction.amount(it.price)
                    )
                    .createdAt()
                    .updateAt()
            }
}
