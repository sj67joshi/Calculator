package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                CalculatorApp()
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var displayText by remember { mutableStateOf("0") }
    var firstNumber by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var isNewOperation by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Display
        Text(
            text = displayText,
            color = Color.White,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp)
        )

        // Buttons
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // First row: C, ±, %, ÷
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("C", Color.Gray, modifier = Modifier.weight(1f)) {
                    displayText = "0"
                    firstNumber = ""
                    operation = ""
                    isNewOperation = true
                }
                CalculatorButton("±", Color.Gray, modifier = Modifier.weight(1f)) {
                    if (displayText != "0") {
                        displayText = if (displayText.startsWith("-")) {
                            displayText.substring(1)
                        } else {
                            "-$displayText"
                        }
                    }
                }
                CalculatorButton("%", Color.Gray, modifier = Modifier.weight(1f)) {
                    val current = displayText.toDoubleOrNull()
                    if (current != null) {
                        displayText = (current / 100).toString().removeSuffix(".0")
                    }
                }
                CalculatorButton("÷", Color(0xFF009688), modifier = Modifier.weight(1f)) {
                    handleOperation(displayText, firstNumber, operation, "÷") { newDisplay, newFirst, newOp ->
                        displayText = newDisplay
                        firstNumber = newFirst
                        operation = newOp
                        isNewOperation = true
                    }
                }
            }

            // Second row: 7, 8, 9, ×
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("7", Color.DarkGray, modifier = Modifier.weight(1f)) {
                    handleNumberInput("7", displayText, isNewOperation) { newDisplay, newIsNew ->
                        displayText = newDisplay
                        isNewOperation = newIsNew
                    }
                }
                CalculatorButton("8", Color.DarkGray, modifier = Modifier.weight(1f)) {
                    handleNumberInput("8", displayText, isNewOperation) { newDisplay, newIsNew ->
                        displayText = newDisplay
                        isNewOperation = newIsNew
                    }
                }
                CalculatorButton("9", Color.DarkGray, modifier = Modifier.weight(1f)) {
                    handleNumberInput("9", displayText, isNewOperation) { newDisplay, newIsNew ->
                        displayText = newDisplay
                        isNewOperation = newIsNew
                    }
                }
                CalculatorButton("×", Color(0xFF009688), modifier = Modifier.weight(1f)) {
                    handleOperation(displayText, firstNumber, operation, "×") { newDisplay, newFirst, newOp ->
                        displayText = newDisplay
                        firstNumber = newFirst
                        operation = newOp
                        isNewOperation = true
                    }
                }
            }

            // Third row: 4, 5, 6, -
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("4", Color.DarkGray, modifier = Modifier.weight(1f)) {
                    handleNumberInput("4", displayText, isNewOperation) { newDisplay, newIsNew ->
                        displayText = newDisplay
                        isNewOperation = newIsNew
                    }
                }
                CalculatorButton("5", Color.DarkGray, modifier = Modifier.weight(1f)) {
                    handleNumberInput("5", displayText, isNewOperation) { newDisplay, newIsNew ->
                        displayText = newDisplay
                        isNewOperation = newIsNew
                    }
                }
                CalculatorButton("6", Color.DarkGray, modifier = Modifier.weight(1f)) {
                    handleNumberInput("6", displayText, isNewOperation) { newDisplay, newIsNew ->
                        displayText = newDisplay
                        isNewOperation = newIsNew
                    }
                }
                CalculatorButton("-", Color(0xFF009688), modifier = Modifier.weight(1f)) {
                    handleOperation(displayText, firstNumber, operation, "-") { newDisplay, newFirst, newOp ->
                        displayText = newDisplay
                        firstNumber = newFirst
                        operation = newOp
                        isNewOperation = true
                    }
                }
            }

            // Fourth row: 1, 2, 3, +
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("1", Color.DarkGray, modifier = Modifier.weight(1f)) {
                    handleNumberInput("1", displayText, isNewOperation) { newDisplay, newIsNew ->
                        displayText = newDisplay
                        isNewOperation = newIsNew
                    }
                }
                CalculatorButton("2", Color.DarkGray, modifier = Modifier.weight(1f)) {
                    handleNumberInput("2", displayText, isNewOperation) { newDisplay, newIsNew ->
                        displayText = newDisplay
                        isNewOperation = newIsNew
                    }
                }
                CalculatorButton("3", Color.DarkGray, modifier = Modifier.weight(1f)) {
                    handleNumberInput("3", displayText, isNewOperation) { newDisplay, newIsNew ->
                        displayText = newDisplay
                        isNewOperation = newIsNew
                    }
                }
                CalculatorButton("+", Color(0xFF009688), modifier = Modifier.weight(1f)) {
                    handleOperation(displayText, firstNumber, operation, "+") { newDisplay, newFirst, newOp ->
                        displayText = newDisplay
                        firstNumber = newFirst
                        operation = newOp
                        isNewOperation = true
                    }
                }
            }

            // Fifth row: 0, ., =
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton("0", Color.DarkGray, modifier = Modifier.weight(2f)) {
                    handleNumberInput("0", displayText, isNewOperation) { newDisplay, newIsNew ->
                        displayText = newDisplay
                        isNewOperation = newIsNew
                    }
                }
                CalculatorButton(".", Color.DarkGray, modifier = Modifier.weight(1f)) {
                    if (!displayText.contains(".")) {
                        displayText += "."
                        isNewOperation = false
                    }
                }
                CalculatorButton("=", Color(0xFF009688), modifier = Modifier.weight(1f)) {
                    if (firstNumber.isNotEmpty() && operation.isNotEmpty()) {
                        val result = calculateResult(firstNumber.toDouble(), displayText.toDouble(), operation)
                        displayText = if (result % 1.0 == 0.0) {
                            result.toInt().toString()
                        } else {
                            result.toString()
                        }
                        firstNumber = ""
                        operation = ""
                        isNewOperation = true
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun handleNumberInput(
    digit: String,
    currentDisplay: String,
    isNewOperation: Boolean,
    onUpdate: (newDisplay: String, newIsNewOperation: Boolean) -> Unit
) {
    if (isNewOperation) {
        onUpdate(digit, false)
    } else {
        val newDisplay = if (currentDisplay == "0") digit else currentDisplay + digit
        onUpdate(newDisplay, false)
    }
}

fun handleOperation(
    currentDisplay: String,
    firstNumber: String,
    currentOperation: String,
    newOperation: String,
    onUpdate: (newDisplay: String, newFirst: String, newOperation: String) -> Unit
) {
    if (firstNumber.isEmpty()) {
        onUpdate(currentDisplay, currentDisplay, newOperation)
    } else if (currentOperation.isNotEmpty()) {
        val result = calculateResult(firstNumber.toDouble(), currentDisplay.toDouble(), currentOperation)
        val resultString = if (result % 1.0 == 0.0) {
            result.toInt().toString()
        } else {
            result.toString()
        }
        onUpdate(resultString, resultString, newOperation)
    }
}

fun calculateResult(first: Double, second: Double, operation: String): Double {
    return when (operation) {
        "+" -> first + second
        "-" -> first - second
        "×" -> first * second
        "÷" -> if (second != 0.0) first / second else 0.0
        else -> 0.0
    }
}
