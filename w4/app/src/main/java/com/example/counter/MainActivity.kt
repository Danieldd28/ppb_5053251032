package com.example.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Counter()
            }
        }
    }
}

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "$count", style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Button(onClick = { count-- }) { Text("-") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { count++ }) { Text("+") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { count = 0 }) { Text("Reset") }
        }
    }
}
