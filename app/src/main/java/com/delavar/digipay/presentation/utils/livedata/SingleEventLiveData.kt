package com.delavar.digipay.presentation.utils.livedata

import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Custom wrapper for [MutableLiveData] that calls observer only one time
 * @param <T>
</T> */
class SingleEventLiveData<T> : MutableLiveData<T>() {
    var mPending: AtomicBoolean = AtomicBoolean(false)
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        super.observe(owner, object : Observer<T> {
            override fun onChanged(t: T) {
                if (mPending.compareAndSet(true, false))
                    observer.onChanged(t)
            }
        })
    }

    override fun setValue(value: T) {
        mPending.set(true)
        super.setValue(value)
    }
}