// FILE: app/src/main/java/com/example/archidemo1/mvi/CounterIntent.kt

package com.example.archidemo1.mvi

// ---------------------------------------------------------
// MVI — Intent (sealed class).
// Every possible user action is listed here.
// The View can ONLY communicate with the ViewModel by
// sending one of these — nothing else can change state.
// The compiler enforces exhaustiveness in when() expressions.
// ---------------------------------------------------------
sealed class CounterIntent {
    object Increment : CounterIntent()
    object Decrement : CounterIntent()
    object Reset     : CounterIntent()
}