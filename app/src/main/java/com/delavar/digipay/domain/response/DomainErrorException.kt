package com.delavar.digipay.domain.response

class DomainErrorException(val errorModel: ErrorModel?) : Throwable()