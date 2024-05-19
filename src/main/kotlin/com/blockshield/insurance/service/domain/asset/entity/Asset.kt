package com.blockshield.insurance.service.domain.asset.entity

import com.blockshield.insurance.service.domain.asset.entity.dto.Price
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime
import java.util.*

@Document("assets")
class Asset {

    @Id
    @Indexed
    var id: UUID? = null

    @NotNull
    var name: String? = null

    @NotNull
    @Indexed(unique = true)
    var initial: String? = null

    @NotNull
    var rating: Double? = null

    @NotNull
    var description: String? = null

    @Indexed
    var active: Boolean? = false

    @NotNull
    var totalSupply: Long? = null

    var currentSupply: Long? = null

    @NotNull
    var price: Price? = null

    @CreatedDate
    var createdAt: OffsetDateTime? = null

    @LastModifiedDate
    var updatedAt: OffsetDateTime? = null

    @Version
    var version: Int? = null

    fun inactivate() = this.apply { this.active = false }
}
