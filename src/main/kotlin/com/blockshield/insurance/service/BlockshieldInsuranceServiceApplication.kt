package com.blockshield.insurance.service

import com.blockshield.insurance.service.application.config.BlockchainNodeClientConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(value = [BlockchainNodeClientConfig::class])
@SpringBootApplication
class BlockshieldInsuranceServiceApplication

fun main(args: Array<String>) {
    runApplication<BlockshieldInsuranceServiceApplication>(*args)
}
