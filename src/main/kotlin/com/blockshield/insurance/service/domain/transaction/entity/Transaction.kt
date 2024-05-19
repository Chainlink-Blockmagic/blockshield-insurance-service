package com.blockshield.insurance.service.domain.transaction.entity

import com.blockshield.insurance.service.domain.transaction.entity.dto.AssetTransaction
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime
import java.util.*

@Document("transactions")
class Transaction {

    @Id
    @Indexed
    var id: UUID? = null

    @NotNull
    @Indexed(unique = true)
    var hash: String? = null

    @NotNull
    @Indexed
    var wallet: String? = null

    @NotNull
    var assetTransaction: AssetTransaction? = null

    @CreatedDate
    var createdAt: OffsetDateTime? = null

    @LastModifiedDate
    var updatedAt: OffsetDateTime? = null

    @Version
    var version: Int? = null

    fun uuid(id: UUID? = UUID.randomUUID()) = this.apply { this.id = id }

    fun hash(hash: String) = this.apply { this.hash = hash }

    fun wallet(wallet: String) = this.apply { this.wallet = wallet }

    fun assetTransaction(assetTransaction: AssetTransaction) =
        this.apply { this.assetTransaction = assetTransaction }

    fun createdAt() = this.apply { this.createdAt = OffsetDateTime.now() }

    fun updateAt() = this.apply { this.updatedAt = OffsetDateTime.now() }
}
