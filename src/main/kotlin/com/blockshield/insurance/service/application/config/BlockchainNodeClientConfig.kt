package com.blockshield.insurance.service.application.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.node")
data class BlockchainNodeClientConfig(
    val client: String,
    val websocket: String,
    val apiKey: String
)
