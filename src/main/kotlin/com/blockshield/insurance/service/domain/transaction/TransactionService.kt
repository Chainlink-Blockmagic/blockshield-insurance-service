package com.blockshield.insurance.service.domain.transaction

import com.blockshield.insurance.service.domain.asset.AssetService
import com.blockshield.insurance.service.domain.transaction.entity.Transaction
import com.blockshield.insurance.service.domain.transaction.entity.dto.CreateTransactionCommand
import com.blockshield.insurance.service.resources.transaction.TransactionRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService(
    private val assetService: AssetService,
    private val transactionRepository: TransactionRepository
) {

    fun create(command: CreateTransactionCommand): UUID =
        command.toTransaction(assetService)
            .run {
                transactionRepository.save(this).id!!
            }

    fun getByWallet(wallet: String, pageable: Pageable): List<Transaction> =
        transactionRepository.findByWallet(wallet, pageable).content
}
