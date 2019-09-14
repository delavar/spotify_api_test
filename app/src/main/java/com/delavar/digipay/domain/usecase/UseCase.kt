package com.delavar.digipay.domain.usecase

import com.delavar.digipay.domain.DomainScheduler
import com.delavar.digipay.domain.response.ErrorModel

abstract class UseCase<T>(val domainScheduler: DomainScheduler) {
    abstract fun execute(onResponse:(T?)->Unit,onError:(ErrorModel?)-> Unit)
}