package com.delavar.digipay.domain.response

data class ErrorModel(val status: ErrorStatus, val code: Int?, val message: String?) {
    constructor(status: ErrorStatus) : this(status, null, null)
    constructor(status: ErrorStatus, message: String) : this(status, null, message)
}
