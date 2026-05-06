// FILE: app/src/main/java/com/example/archidemo1/mvvm/CounterUiState.kt

package com.example.archidemo1.mvvm

// ---------------------------------------------------------
// MVVM — UI State.
// Single immutable data class. The ViewModel emits new
// copies of this via StateFlow. The View never writes to it.
// ---------------------------------------------------------
data class CounterUiState(
    val count:        Int     = 0,
    val isLoading:    Boolean = false,
    val errorMessage: String? = null
)