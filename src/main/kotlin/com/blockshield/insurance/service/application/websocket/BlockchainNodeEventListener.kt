package com.blockshield.insurance.service.application.websocket

import com.blockshield.insurance.service.application.config.BlockchainNodeClientConfig
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.web3j.eth2.api.BeaconNodeClientFactory
import org.web3j.eth2.api.BeaconNodeService
import org.web3j.eth2.api.schema.BeaconEventType
import java.util.*

@Configuration
class NodeServiceConfig(private val blockchainNodeClientConfig: BlockchainNodeClientConfig) {

    @Bean
    fun beaconNodeService(): BeaconNodeService = BeaconNodeService(blockchainNodeClientConfig.client)
}

@Component
class BlockchainNodeEventListener(
    private val blockchainNodeClientConfig: BlockchainNodeClientConfig,
    private val service: BeaconNodeService
) {
    private val logger = LoggerFactory.getLogger(BlockchainNodeEventListener::class.java)

    @PostConstruct
    fun init() {
        logger.info("Starting blockchain node event listener!")

        val topics = EnumSet.allOf(BeaconEventType::class.java)
        try {
            BeaconNodeClientFactory.build(service).let {
                it.events.onEvent(topics) { event ->
                    logger.info(
                        """
                            Received event [ $event ] from 
                            RPC host [ ${blockchainNodeClientConfig.client} ]!
                        """.trimIndent()
                    )
                }
            }
        } catch (ex: Exception) {
            logger.error("Error on keep connected to a blockchain node!")
        }
    }

    @PreDestroy
    fun destroy() {
        try {
            service.close()
        } catch (ex: Exception) {
            logger.error("Error on close the beacon node service instance!")
        }
    }
}
