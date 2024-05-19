package com.blockshield.insurance.service.resources.transaction.event

import com.blockshield.insurance.service.domain.transaction.entity.dto.TransactionCheckoutEvent
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class TransactionEventPublisher(private val applicationEventPublisher: ApplicationEventPublisher) {
    private val logger = LoggerFactory.getLogger(TransactionEventPublisher::class.java)
    fun publish(event: TransactionCheckoutEvent) =
        applicationEventPublisher.publishEvent(event).also {
            logger.info("Event [ $event ] to create a transaction published successfully!")
        }
}
