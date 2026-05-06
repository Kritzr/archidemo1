// FILE: app/src/main/java/com/example/archidemo1/mvi/CounterState.kt

package com.example.archidemo1.mvi

// ---------------------------------------------------------
// MVI — State (immutable data class).
// The single source of truth for the entire screen.
// Every render is derived from one instance of this class.
// The Reducer always produces a NEW copy — never mutates.
// ---------------------------------------------------------
data class CounterState(
    val count:        Int     = 0,
    val isLoading:    Boolean = false,
    val errorMessage: String? = null
)