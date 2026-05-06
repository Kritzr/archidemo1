// FILE: app/src/main/java/com/example/archidemo1/mvi/MviCounterScreen.kt

package com.example.archidemo1.mvi

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

// ---------------------------------------------------------
// MVI — View (Composable).
// The View's only two jobs:
//   1. Render the current State
//   2. Emit Intents upward via process() — never mutate state
//
// Notice: buttons call vm.process(CounterIntent.X),
// NOT vm.increment() directly. That single rule enforces
// the entire unidirectional contract.
// ---------------------------------------------------------

@Composable
fun MviCounterScreen(
    modifier: Modifier = Modifier,             // FIX: added modifier param
    vm: CounterViewModel = viewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()

    MviCounterContent(
        modifier = modifier,                   // FIX: pass modifier down
        state    = state,
        onIntent = vm::process
    )
}

// Stateless inner composable — easy to @Preview and test
@Composable
fun MviCounterContent(
    modifier: Modifier = Modifier,
    state:    CounterState,
    onIntent: (CounterIntent) -> Unit
) {
    Column(
        modifier = modifier                    // FIX: use the passed modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text  = "MVI",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text  = "View emits Intent → reduce() → new State.",
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
            // Emitting an Intent — never calling logic directly
            OutlinedButton(onClick = { onIntent(CounterIntent.Decrement) }) { Text("−  Dec") }
            OutlinedButton(onClick = { onIntent(CounterIntent.Reset) })     { Text("Reset") }
            Button(onClick          = { onIntent(CounterIntent.Increment) }) { Text("+  Inc") }
        }
    }
}