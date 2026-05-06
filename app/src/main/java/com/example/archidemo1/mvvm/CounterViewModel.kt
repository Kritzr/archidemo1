// FILE: app/src/main/java/com/example/archidemo1/mvvm/CounterViewModel.kt

package com.example.archidemo1.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// ---------------------------------------------------------
// MVVM — ViewModel.
// Survives configuration changes (screen rotation).
// Exposes a read-only StateFlow the Composable collects.
// Never holds a reference to Context, Activity, or View.
// The _uiState is private so only this class can mutate it.
// ---------------------------------------------------------
class CounterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState = _uiState.asStateFlow()

    fun increment() {
        _uiState.update { it.copy(count = it.count + 1) }
    }

    fun decrement() {
        _uiState.update { it.copy(count = it.count - 1) }
    }

    fun reset() {
        _uiState.update { it.copy(count = 0) }
    }
}