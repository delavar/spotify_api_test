package com.delavar.digipay.domain.usecase

import Artist
import com.delavar.digipay.domain.DomainScheduler
import com.delavar.digipay.domain.repository.SearchRepository
import com.delavar.digipay.domain.response.DomainErrorException
import com.delavar.digipay.domain.response.ErrorModel
import com.delavar.digipay.domain.response.ErrorStatus

class SearchUseCase(
    domainScheduler: DomainScheduler,
    val searchRepository: SearchRepository
) : UseCase<List<Artist>>(domainScheduler) {

    var limit: Int = 20
    var offset: Int = 0

    fun setParam(offset: Int, limit: Int): SearchUseCase {
        this.offset = offset
        this.limit = limit
        return this
    }

    override fun execute(onResponse: (List<Artist>?) -> Unit, onError: (ErrorModel?) -> Unit) {
        searchRepository.getResult(offset, limit)
            .observeOn(domainScheduler.ui())
            .subscribeOn(domainScheduler.io())
            .subscribe({
                onResponse.invoke(it)
            }, {
                if (it is DomainErrorException)
                    onError.invoke(it.errorModel)
                else
                    onError.invoke(ErrorModel(ErrorStatus.UNKOWN_ERROR))
            })
    }
}