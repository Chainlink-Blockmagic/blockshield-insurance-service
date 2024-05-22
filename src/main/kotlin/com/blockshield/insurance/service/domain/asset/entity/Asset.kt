package com.blockshield.insurance.service.domain.asset.entity

import com.blockshield.insurance.service.domain.asset.entity.dto.Price
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*

@Document("assets")
class Asset : Serializable {

    @Id
    @Indexed
    var id: UUID? = null

    @NotNull
    @Indexed
    var name: String? = null

    @NotNull
    @Indexed(unique = true)
    var symbol: String? = null

    @NotNull
    var rating: String? = null

    @NotNull
    var description: String? = null

    @NotNull
    var observation: String? = null

    @NotNull
    var tokenizationPlatform: String? = null

    @Indexed
    var active: Boolean? = false

    @NotNull
    var totalSupply: Long? = null

    var remainingSupply: Long? = null

    @NotNull
    var price: Price? = null

    @NotNull
    var dueDate: LocalDate? = null

    @CreatedDate
    var createdAt: OffsetDateTime? = null

    @LastModifiedDate
    var updatedAt: OffsetDateTime? = null

    @Version
    var version: Int? = null

    fun inactivate() = this.apply { this.active = false }

    fun updateAt() = this.apply { this.updatedAt = OffsetDateTime.now() }

    fun totalValue(): BigDecimal = this.price?.let {
        val valueToMultiply = this.totalSupply ?: MIN_TOTAL_SUPPLY
        it.unitaryValue.multiply(BigDecimal(valueToMultiply))
    } ?: BigDecimal.ZERO

    companion object {
        private const val MIN_TOTAL_SUPPLY = 0L
    }
}
