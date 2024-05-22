package com.blockshield.insurance.service.domain.transaction.entity.dto

import com.blockshield.insurance.service.domain.asset.entity.dto.Price
import java.math.BigDecimal
import java.util.*

data class AssetTransaction(
    val id: UUID,
    val symbol: String,
    val quantity: Int
) {
    private var amount: BigDecimal? = BigDecimal.ZERO

    fun getAmount(): BigDecimal = this.amount ?: BigDecimal.ZERO

    fun amount(price: Price?) = this.apply {
        requireNotNull(price) { "The Price object cannot be null!" }

        this.amount = price.unitaryValue.multiply(BigDecimal(quantity))
    }
}
