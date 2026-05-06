// FILE: app/src/main/java/com/example/archidemo1/mvc/MvcCounterScreen.kt

package com.example.archidemo1.mvc

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.archidemo1.shared.CounterModel

// ---------------------------------------------------------
// MVC — The Activity (Controller) does everything.
// Model is updated directly and the View is refreshed
// manually after every mutation. No separation of concerns.
// ---------------------------------------------------------

@Composable
fun MvcCounterScreen(modifier: Modifier = Modifier) {
    // FIX: modifier parameter added so MainActivity can pass innerPadding
    var model by remember { mutableStateOf(CounterModel()) }

    Column(
        modifier = modifier                        // FIX: use the passed modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text  = "MVC",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text  = "Controller (this composable) reads from\nModel AND pushes to View directly.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(40.dp))

        // VIEW — display model data
        Text(
            text  = model.count.toString(),
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(Modifier.height(32.dp))

        // CONTROLLER — user event → mutate Model → update View
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(onClick = { model = model.decrement() }) { Text("−  Dec") }
            OutlinedButton(onClick = { model = model.reset() })     { Text("Reset") }
            Button(onClick          = { model = model.increment() }) { Text("+  Inc") }
        }
    }
}