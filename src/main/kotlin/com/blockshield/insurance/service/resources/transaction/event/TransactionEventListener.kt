package com.blockshield.insurance.service.resources.transaction.event

import com.blockshield.insurance.service.domain.transaction.TransactionService
import com.blockshield.insurance.service.domain.transaction.entity.dto.TransactionCheckoutEvent
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TransactionEventListener(private val transactionService: TransactionService) {
    private val logger = LoggerFactory.getLogger(TransactionEventListener::class.java)

    @EventListener(classes = [TransactionCheckoutEvent::class])
    fun consume(event: TransactionCheckoutEvent) =
        event.toCreateTransactionCommand().let {
            transactionService.create(it)
        }.also {
            logger.info("Transaction [ $it ] to created successfully!")
        }
}
