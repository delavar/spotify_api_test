package com.delavar.digipay.data.source.cloud

import SearchResponse
import com.delavar.digipay.data.ErrorUtils
import com.delavar.digipay.data.restful.ApiService
import io.reactivex.Flowable

class CloudSourceImpl(val service: ApiService, val errorUtils: ErrorUtils) : CloudSource {
    override fun search(offset: Int, limit: Int): Flowable<SearchResponse> {
        return service.search(offset, limit).onErrorResumeNext { t: Throwable ->
            Flowable.error(errorUtils.getErrorModel(t))
        }
    }

}