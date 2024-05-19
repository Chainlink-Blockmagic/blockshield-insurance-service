package com.blockshield.insurance.service.application.web.resource

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    value = ["/v1/api/transactions"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class TransactionResource