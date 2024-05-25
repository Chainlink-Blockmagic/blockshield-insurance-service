package com.blockshield.insurance.service.resources.transaction

import com.blockshield.insurance.service.domain.transaction.entity.Transaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.UUID

interface TransactionRepository : MongoRepository<Transaction, UUID> {

    @Query("{ 'wallet' : {\$regex : ?0, \$options: 'i'}}")
    fun findByWallet(wallet: String, pageable: Pageable): Page<Transaction>
}
