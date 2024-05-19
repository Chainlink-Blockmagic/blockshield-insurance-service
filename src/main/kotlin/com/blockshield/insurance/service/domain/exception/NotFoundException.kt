package com.blockshield.insurance.service.domain.exception

import java.lang.RuntimeException


class NotFoundException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

}
