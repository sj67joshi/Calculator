package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var display by remember { mutableStateOf("0") }
    var history by remember { mutableStateOf("") }
    var currentOperation by remember { mutableStateOf<String?>(null) }
    var previousValue by remember { mutableStateOf<Double?>(null) }
    var newNumber by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // History display
        if (history.isNotEmpty()) {
            Text(
                text = history,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.padding(end = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Main display
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Text(
                text = display,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Calculator buttons grid
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Row 1: C, ÷, ×, Backspace
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "C",
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.error,
                    onClick = {
                        display = "0"
                        history = ""
                        currentOperation = null
                        previousValue = null
                        newNumber = true
                    }
                )
                CalculatorButton(
                    text = "÷",
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.primary,
                    onClick = {
                        if (previousValue != null && currentOperation != null && !newNumber) {
                            val result = calculate(previousValue!!, display.toDouble(), currentOperation)
                            display = result
                            previousValue = result.toDoubleOrNull()
                        }
                        currentOperation = "÷"
                        previousValue = display.toDoubleOrNull()
                        newNumber = true
                    }
                )
                CalculatorButton(
                    text = "×",
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.primary,
                    onClick = {
                        if (previousValue != null && currentOperation != null && !newNumber) {
                            val result = calculate(previousValue!!, display.toDouble(), currentOperation)
                            display = result
                            previousValue = result.toDoubleOrNull()
                        }
                        currentOperation = "×"
                        previousValue = display.toDoubleOrNull()
                        newNumber = true
                    }
                )
                CalculatorButton(
                    text = "⌫",
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.secondary,
                    onClick = {
                        if (display.length > 1) {
                            display = display.dropLast(1)
                        } else {
                            display = "0"
                            newNumber = true
                        }
                    }
                )
            }

            // Row 2: 7, 8, 9, -
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "7",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        display = appendDigit("7", display, newNumber)
                        newNumber = false
                    }
                )
                CalculatorButton(
                    text = "8",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        display = appendDigit("8", display, newNumber)
                        newNumber = false
                    }
                )
                CalculatorButton(
                    text = "9",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        display = appendDigit("9", display, newNumber)
                        newNumber = false
                    }
                )
                CalculatorButton(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.primary,
                    onClick = {
                        if (previousValue != null && currentOperation != null && !newNumber) {
                            val result = calculate(previousValue!!, display.toDouble(), currentOperation)
                            display = result
                            previousValue = result.toDoubleOrNull()
                        }
                        currentOperation = "-"
                        previousValue = display.toDoubleOrNull()
                        newNumber = true
                    }
                )
            }

            // Row 3: 4, 5, 6, +
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "4",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        display = appendDigit("4", display, newNumber)
                        newNumber = false
                    }
                )
                CalculatorButton(
                    text = "5",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        display = appendDigit("5", display, newNumber)
                        newNumber = false
                    }
                )
                CalculatorButton(
                    text = "6",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        display = appendDigit("6", display, newNumber)
                        newNumber = false
                    }
                )
                CalculatorButton(
                    text = "+",
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.primary,
                    onClick = {
                        if (previousValue != null && currentOperation != null && !newNumber) {
                            val result = calculate(previousValue!!, display.toDouble(), currentOperation)
                            display = result
                            previousValue = result.toDoubleOrNull()
                        }
                        currentOperation = "+"
                        previousValue = display.toDoubleOrNull()
                        newNumber = true
                    }
                )
            }

            // Row 4: 1, 2, 3, =
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "1",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        display = appendDigit("1", display, newNumber)
                        newNumber = false
                    }
                )
                CalculatorButton(
                    text = "2",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        display = appendDigit("2", display, newNumber)
                        newNumber = false
                    }
                )
                CalculatorButton(
                    text = "3",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        display = appendDigit("3", display, newNumber)
                        newNumber = false
                    }
                )
                CalculatorButton(
                    text = "=",
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.tertiary,
                    onClick = {
                        if (previousValue != null && currentOperation != null) {
                            history = "$previousValue $currentOperation $display"
                            val result = calculate(previousValue!!, display.toDouble(), currentOperation)
                            display = result
                            currentOperation = null
                            previousValue = null
                            newNumber = true
                        }
                    }
                )
            }

            // Row 5: 0 (span 2), ., (empty)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "0",
                    modifier = Modifier.weight(2f),
                    onClick = {
                        display = appendDigit("0", display, newNumber)
                        newNumber = false
                    }
                )
                CalculatorButton(
                    text = ".",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        if (!display.contains(".")) {
                            display += "."
                            newNumber = false
                        }
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun appendDigit(digit: String, currentDisplay: String, isNewNumber: Boolean): String {
    return if (isNewNumber) digit else currentDisplay + digit
}

fun calculate(
    previousValue: Double,
    currentValue: Double,
    operation: String?
): String {
    val result = when (operation) {
        "+" -> previousValue + currentValue
        "-" -> previousValue - currentValue
        "×" -> previousValue * currentValue
        "÷" -> if (currentValue != 0.0) previousValue / currentValue else Double.NaN
        else -> currentValue
    }
    return if (result == result) result.toString() else "Error"
}