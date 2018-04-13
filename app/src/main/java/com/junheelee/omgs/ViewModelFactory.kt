package com.junheelee.omgs

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.junheelee.omgs.home.HomeViewModel

/**
 * Created by junhee.lee on 2018. 4. 13..
 */
class ViewModelFactory private constructor(
        private val app: Application
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(HomeViewModel::class.java) ->
                        HomeViewModel(app)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class : ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(app: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(app).also { INSTANCE = it }
                }

        fun destoryInstance(){
            INSTANCE = null
        }
    }
}