package com.blockshield.insurance.service.resources.asset.event

import com.blockshield.insurance.service.domain.asset.entity.Asset
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class AssetMongoEventListener : AbstractMongoEventListener<Asset>() {

    override fun onBeforeConvert(event: BeforeConvertEvent<Asset>) {
        if (event.source.id == null) {
            event.source.id = UUID.randomUUID()
        }
    }

}
