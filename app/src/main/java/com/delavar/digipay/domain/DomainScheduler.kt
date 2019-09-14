package com.delavar.digipay.domain

import io.reactivex.Scheduler

interface DomainScheduler {
    fun io(): Scheduler
    fun ui(): Scheduler
}