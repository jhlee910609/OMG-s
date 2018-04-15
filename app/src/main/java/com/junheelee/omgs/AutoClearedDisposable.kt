package com.junheelee.omgs

import android.arch.lifecycle.LifecycleObserver
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

class AutoClearedDisposable(
        private val lifecycleOwner: AppCompatActivity,
        private val alwaysClearOnStop: Boolean = true,
        private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : LifecycleObserver {



}