// FILE: app/src/main/java/com/example/archidemo1/mvvm/MvvmCounterScreen.kt

package com.example.archidemo1.mvvm

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

// ---------------------------------------------------------
// MVVM — View (Composable).
// Collects StateFlow with collectAsStateWithLifecycle() so
// collection stops automatically when the app is backgrounded.
// The View has zero logic — it maps state → UI and fires
// events upward as plain function calls on the ViewModel.
// ---------------------------------------------------------

@Composable
fun MvvmCounterScreen(
    modifier: Modifier = Modifier,             // FIX: added modifier param
    vm: CounterViewModel = viewModel()
) {
    val state by vm.uiState.collectAsStateWithLifecycle()

    MvvmCounterContent(
        modifier    = modifier,                // FIX: pass modifier down
        state       = state,
        onIncrement = vm::increment,
        onDecrement = vm::decrement,
        onReset     = vm::reset
    )
}

// Stateless inner composable — easy to @Preview without a ViewModel
@Composable
fun MvvmCounterContent(
    modifier:    Modifier = Modifier,
    state:       CounterUiState,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onReset:     () -> Unit
) {
    Column(
        modifier = modifier                    // FIX: use the passed modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text  = "MVVM",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text  = "View collects StateFlow.\nViewModel survives rotation.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(40.dp))

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(
                text  = state.count.toString(),
                style = MaterialTheme.typography.displayLarge
            )
        }

        state.errorMessage?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(onClick = onDecrement) { Text("−  Dec") }
            OutlinedButton(onClick = onReset)     { Text("Reset") }
            Button(onClick          = onIncrement) { Text("+  Inc") }
        }
    }
}