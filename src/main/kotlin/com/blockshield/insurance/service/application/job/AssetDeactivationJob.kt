package com.blockshield.insurance.service.application.job

import com.blockshield.insurance.service.domain.asset.AssetService
import com.blockshield.insurance.service.resources.asset.AssetRepository
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class AssetDeactivationJob(
    private val assetService: AssetService,
    private val assetRepository: AssetRepository
) {
    private val logger = LoggerFactory.getLogger(AssetDeactivationJob::class.java)

    /**
     * https://www.javainuse.com/cron
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    fun deactivateDueDateAssets() {
        val currentDate = LocalDate.now()
        logger.info("Starting to deactivate all assets with due dates in [ $currentDate ]!")

        assetRepository.findByDueDateLessThanAndActiveIsTrue(currentDate).let { assets ->
            if (assets.isEmpty())
                logger.info("Assets with due dates in [ $currentDate ] are not found!")

            assets.forEach { asset ->
                if (asset.id != null) {
                    assetService.inactivate(asset.id!!).also {
                        logger.info("Asset [ ${asset.id} ] was inactivated on [ $currentDate ]!")
                    }
                }
            }
        }
    }
}
