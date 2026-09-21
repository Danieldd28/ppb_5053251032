package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val KEYS = listOf(
    listOf("C", "⌫", "÷"),
    listOf("7", "8", "9", "×"),
    listOf("4", "5", "6", "-"),
    listOf("1", "2", "3", "+"),
    listOf("0", ".", "="),
)

private const val ERROR = "Error"

class CalculatorState {
    var display by mutableStateOf("0")
        private set
    private var accumulator: Double? = null
    private var operator: String? = null
    private var isFresh = true // next digit replaces the display

    fun press(key: String) = when {
        key == "C" -> reset()
        key == "⌫" -> backspace()
        key == "=" -> equals()
        key in "+-×÷" -> setOperator(key)
        else -> appendDigit(key)
    }

    private fun reset() {
        display = "0"; accumulator = null; operator = null; isFresh = true
    }

    private fun backspace() {
        display = if (!isFresh && display.length > 1) display.dropLast(1) else "0"
    }

    private fun appendDigit(key: String) {
        display = when {
            isFresh -> if (key == ".") "0." else key
            key == "." && "." in display -> display
            display == "0" && key != "." -> key
            else -> display + key
        }
        isFresh = false
    }

    private fun setOperator(key: String) {
        if (!isFresh) evaluatePending()
        accumulator = display.toDoubleOrNull()
        operator = key
        isFresh = true
    }

    private fun equals() {
        evaluatePending()
        accumulator = null
        operator = null
        isFresh = true
    }

    private fun evaluatePending() {
        val left = accumulator ?: return
        val right = display.toDoubleOrNull() ?: return
        val value = when (operator) {
            "+" -> left + right
            "-" -> left - right
            "×" -> left * right
            "÷" -> left / right
            else -> return
        }
        display = format(value)
        accumulator = value.takeIf { it.isFinite() }
    }

    private fun format(value: Double) = when {
        !value.isFinite() -> ERROR
        value == value.toLong().toDouble() -> value.toLong().toString()
        else -> value.toString()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { Calculator() } }
    }
}

@Composable
fun Calculator() {
    val state = remember { CalculatorState() }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Bottom),
    ) {
        Text(
            text = state.display,
            fontSize = 56.sp,
            maxLines = 1,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        )
        KEYS.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { key ->
                    Button(
                        onClick = { state.press(key) },
                        modifier = Modifier.weight(1f).height(72.dp),
                    ) { Text(key, fontSize = 24.sp) }
                }
            }
        }
    }
}
