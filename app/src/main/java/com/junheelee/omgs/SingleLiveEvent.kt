package com.junheelee.omgs

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by junhee.lee on 2018. 4. 13..
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        if (hasActiveObservers()){
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }
        super.observe(owner, Observer<T> {
            if (pending.compareAndSet(true, false)){
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(value: T) {
        pending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {

    }

    companion object {
        private const val TAG = "SingleLiveEvent"
    }
}