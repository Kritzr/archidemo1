// FILE: app/src/main/java/com/example/archidemo1/mvi/CounterViewModel.kt

package com.example.archidemo1.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// ---------------------------------------------------------
// MVI — ViewModel (contains the Reducer).
//
// process(intent) is the ONLY entry point for state changes.
// reduce() is a PURE FUNCTION:
//   - no side effects
//   - no I/O or coroutines
//   - same inputs always produce the same output
//   - trivially unit-testable with no mocks
// ---------------------------------------------------------
class CounterViewModel : ViewModel() {

    private val _state = MutableStateFlow(CounterState())
    val state = _state.asStateFlow()

    /** Single entry point — the View calls this for every action */
    fun process(intent: CounterIntent) {
        _state.update { currentState -> reduce(currentState, intent) }
    }

    /** Pure function — no side effects allowed here */
    private fun reduce(state: CounterState, intent: CounterIntent): CounterState =
        when (intent) {
            is CounterIntent.Increment -> state.copy(count = state.count + 1)
            is CounterIntent.Decrement -> state.copy(count = state.count - 1)
            is CounterIntent.Reset     -> state.copy(count = 0)
        }
}