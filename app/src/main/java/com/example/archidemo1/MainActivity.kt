// FILE: app/src/main/java/com/example/archidemo1/MainActivity.kt

package com.example.archidemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.archidemo1.mvc.MvcCounterScreen
import com.example.archidemo1.mvp.MvpCounterScreen
import com.example.archidemo1.mvvm.MvvmCounterScreen
import com.example.archidemo1.mvi.MviCounterScreen
import com.example.archidemo1.ui.theme.Archidemo1Theme

data class PatternTab(val route: String, val label: String)

val tabs = listOf(
    PatternTab("mvc",  "MVC"),
    PatternTab("mvp",  "MVP"),
    PatternTab("mvvm", "MVVM"),
    PatternTab("mvi",  "MVI")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Archidemo1Theme {
                ArchPatternsApp()
            }
        }
    }
}

@Composable
fun ArchPatternsApp() {
    var selectedRoute by remember { mutableStateOf("mvc") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedRoute == tab.route,
                        onClick  = { selectedRoute = tab.route },
                        label    = { Text(tab.label) },
                        icon     = { Icon(Icons.Default.Code, contentDescription = tab.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // FIX: innerPadding is now applied so content never clips behind the nav bar
        when (selectedRoute) {
            "mvc"  -> MvcCounterScreen(modifier  = Modifier.padding(innerPadding))
            "mvp"  -> MvpCounterScreen(modifier  = Modifier.padding(innerPadding))
            "mvvm" -> MvvmCounterScreen(modifier = Modifier.padding(innerPadding))
            "mvi"  -> MviCounterScreen(modifier  = Modifier.padding(innerPadding))
        }
    }
}