package com.blockshield.insurance.service.resources.asset.event

import com.blockshield.insurance.service.domain.asset.AssetService
import com.blockshield.insurance.service.domain.transaction.entity.Transaction
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class AssetRemainingSupplyUpdateEventListener(private val assetService: AssetService) {
    private val logger = LoggerFactory.getLogger(AssetRemainingSupplyUpdateEventListener::class.java)

    @EventListener(classes = [Transaction::class])
    fun consume(event: Transaction) {
        event.assetTransaction?.let { assetTransaction ->
            assetService.get(assetTransaction.id)
                .let {
                    assetService.update(
                        assetTransaction.id,
                        it.updateRemainingSupply(assetTransaction.quantity)
                    )
                }
                .also {
                    logger.info(
                        """
                        Updated asset remaining supply with quantity [ ${assetTransaction.quantity} ] 
                        for id [ ${assetTransaction.id} ] successfully!
                    """.trimIndent()
                    )
                }
        }
    }
}
