package com.delavar.digipay.domain.usecase

import com.delavar.digipay.domain.DomainScheduler
import com.delavar.digipay.domain.response.ErrorModel
import io.reactivex.disposables.CompositeDisposable

abstract class UseCase<T>(val domainScheduler: DomainScheduler) {

    abstract fun execute(
        compositeDisposable: CompositeDisposable,
        onResponse: (T?) -> Unit,
        onError: (ErrorModel?) -> Unit
    )
}