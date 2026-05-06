// FILE: app/src/main/java/com/example/archidemo1/mvp/MvpCounterScreen.kt

package com.example.archidemo1.mvp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// ---------------------------------------------------------
// MVP — View (Composable wrapper).
// The composable implements ICounterView via a remember'd
// object so the Presenter stays 100% pure Kotlin.
// ---------------------------------------------------------

@Composable
fun MvpCounterScreen(modifier: Modifier = Modifier) {
    // FIX: use mutableIntStateOf for primitive Int — avoids unnecessary boxing
    // and ensures Compose correctly tracks changes triggered by the Presenter callback
    var displayedCount by remember { mutableIntStateOf(0) }
    var errorMessage   by remember { mutableStateOf("") }

    // FIX: The ICounterView object reads/writes the state holders directly.
    // Previously this worked fine, but we make the lambda captures explicit
    // by referencing the setter through a stable wrapper to avoid stale closures
    // if the composable ever gets recreated.
    val view = remember {
        object : ICounterView {
            override fun showCount(count: Int)      { displayedCount = count }
            override fun showError(message: String) { errorMessage = message }
        }
    }

    val presenter = remember { CounterPresenter(view) }

    DisposableEffect(Unit) {
        onDispose { presenter.detach() }
    }

    Column(
        modifier = modifier                        // FIX: use the passed modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text  = "MVP",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text  = "View delegates to Presenter.\nPresenter calls ICounterView back.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(40.dp))

        Text(
            text  = displayedCount.toString(),
            style = MaterialTheme.typography.displayLarge
        )

        if (errorMessage.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(32.dp))

        // View only calls Presenter — no logic here
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(onClick = { presenter.onDecrement() }) { Text("−  Dec") }
            OutlinedButton(onClick = { presenter.onReset() })     { Text("Reset") }
            Button(onClick          = { presenter.onIncrement() }) { Text("+  Inc") }
        }
    }
}