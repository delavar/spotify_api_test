package com.delavar.digipay.domain.usecase

import com.delavar.digipay.domain.model.Artist
import com.delavar.digipay.domain.DomainScheduler
import com.delavar.digipay.domain.repository.SearchRepository
import com.delavar.digipay.domain.response.DomainErrorException
import com.delavar.digipay.domain.response.ErrorModel
import com.delavar.digipay.domain.response.ErrorStatus
import io.reactivex.disposables.CompositeDisposable

class SearchUseCase(
    domainScheduler: DomainScheduler,
    val searchRepository: SearchRepository
) : UseCase<List<Artist>>(domainScheduler) {
    var query: String = ""
    var limit: Int = 20
    var offset: Int = 0

    fun setParam(query: String, offset: Int, limit: Int): SearchUseCase {
        this.query = query
        this.offset = offset
        this.limit = limit
        return this
    }

    override fun execute(
        compositeDisposable: CompositeDisposable,
        onResponse: (List<Artist>?) -> Unit,
        onError: (ErrorModel?) -> Unit
    ) {
        searchRepository.getResult(query, offset, limit)
            .observeOn(domainScheduler.ui())
            .subscribeOn(domainScheduler.io())
            .subscribe({
                onResponse.invoke(it)
            }, {
                if (it is DomainErrorException)
                    onError.invoke(it.errorModel)
                else
                    onError.invoke(ErrorModel(ErrorStatus.UNKOWN_ERROR))
            }).also {
                compositeDisposable.add(it)
            }
    }
}