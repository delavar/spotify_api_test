package com.delavar.digipay.data

import com.delavar.digipay.data.model.ErrorResponse
import com.delavar.digipay.R
import com.delavar.digipay.domain.response.DomainErrorException
import com.delavar.digipay.domain.response.ErrorModel
import com.delavar.digipay.domain.response.ErrorStatus
import com.delavar.digipay.presentation.utils.provider.ResourceProvider
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException

class ErrorUtils(
    val resourceProvider: ResourceProvider,
    val gson: Gson
) {

    fun getErrorModel(t: Throwable): DomainErrorException {
        return DomainErrorException(
            when (t) {
                is IOException ->
                    ErrorModel(
                        ErrorStatus.NETWORK_ERROR,
                        resourceProvider.getString(R.string.response_network_error)
                    )
                is HttpException ->
                    if (t.code() == 401)
                        ErrorModel(
                            ErrorStatus.UNAUTHORIZED,
                            resourceProvider.getString(R.string.response_unauthorized_error)
                        )
                    else if (t.code() >= 400 && t.code() < 500) {
                        getHttpError(t.response()?.errorBody())
                    } else if (t.code() >= 500 && t.code() < 600) {
                        ErrorModel(
                            ErrorStatus.SERVER_ERROR,
                            resourceProvider.getString(R.string.response_server_error)
                        )
                    } else {
                        ErrorModel(
                            ErrorStatus.UNKOWN_ERROR,
                            resourceProvider.getString(R.string.response_unknown_error)
                        )
                    }
                is SocketTimeoutException ->
                    ErrorModel(
                        ErrorStatus.TIME_OUT,
                        resourceProvider.getString(R.string.response_timeout_error)
                    )
                else ->
                    ErrorModel(
                        ErrorStatus.UNKOWN_ERROR,
                        resourceProvider.getString(R.string.response_unknown_error)
                    )

            }
        )

    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel]
     */
    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {

            val response = gson.fromJson(body.toString(), ErrorResponse::class.java)
            // use response body to get error detail
            ErrorModel(ErrorStatus.CLIENT_EEROR, response.error.status, response.error.message)
        } catch (e: Exception) {
            e.printStackTrace()
            ErrorModel(ErrorStatus.CLIENT_EEROR)
        }

    }
}