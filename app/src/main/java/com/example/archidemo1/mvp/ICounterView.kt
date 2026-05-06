// FILE: app/src/main/java/com/example/archidemo1/mvp/ICounterView.kt

package com.example.archidemo1.mvp

// ---------------------------------------------------------
// MVP — View contract.
// The Activity implements this interface.
// The Presenter ONLY talks to this — never to the Activity
// class directly. This is what makes the Presenter testable.
// ---------------------------------------------------------
interface ICounterView {
    fun showCount(count: Int)
    fun showError(message: String)
}