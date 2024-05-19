package com.blockshield.insurance.service.resources.transaction

import com.blockshield.insurance.service.domain.transaction.entity.Transaction
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionListener : AbstractMongoEventListener<Transaction>() {

    override fun onBeforeConvert(event: BeforeConvertEvent<Transaction>) {
        if (event.source.id == null) {
            event.source.id = UUID.randomUUID()
        }
    }

}
