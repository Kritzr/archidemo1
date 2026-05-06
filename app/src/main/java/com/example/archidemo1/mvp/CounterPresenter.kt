// FILE: app/src/main/java/com/example/archidemo1/mvp/CounterPresenter.kt

package com.example.archidemo1.mvp

import com.example.archidemo1.shared.CounterModel

// ---------------------------------------------------------
// MVP — Presenter.
// Zero Android imports — runs entirely on the JVM.
// Unit test this class by passing a fake ICounterView.
// Call detach() from onCleared() / onDestroy() to avoid
// memory leaks (Presenter holds a reference to the View).
// ---------------------------------------------------------
class CounterPresenter(private var view: ICounterView?) {

    private var model = CounterModel()

    fun onIncrement() {
        model = model.increment()
        view?.showCount(model.count)
    }

    fun onDecrement() {
        model = model.decrement()
        view?.showCount(model.count)
    }

    fun onReset() {
        model = model.reset()
        view?.showCount(model.count)
    }

    /** Call this when the View is destroyed to prevent memory leaks. */
    fun detach() {
        view = null
    }
}