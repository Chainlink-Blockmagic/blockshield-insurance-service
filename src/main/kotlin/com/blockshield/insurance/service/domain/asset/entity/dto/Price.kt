package com.blockshield.insurance.service.domain.asset.entity.dto

import com.blockshield.insurance.service.domain.asset.entity.type.CurrencyType
import java.math.BigDecimal

data class Price(
    val unitaryValue: BigDecimal,
    val currency: CurrencyType,
    val validity: Validity
)
