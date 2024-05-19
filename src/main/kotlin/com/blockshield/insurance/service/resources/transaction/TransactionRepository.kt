package com.blockshield.insurance.service.resources.transaction

import com.blockshield.insurance.service.domain.transaction.entity.Transaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface TransactionRepository : MongoRepository<Transaction, UUID> {

    fun findByWallet(wallet: String, pageable: Pageable): Page<Transaction>
}
