package com.junheelee.omgs

import io.reactivex.disposables.Disposable

operator fun AutoClearedDisposable.plusAssign(disposable: Disposable) = this.add(disposable)