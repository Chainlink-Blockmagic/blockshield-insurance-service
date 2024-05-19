package com.blockshield.insurance.service.domain.common

fun interface Mapper<I,O> {

    fun mapFrom(input: I, auxiliary: O?): O
}
