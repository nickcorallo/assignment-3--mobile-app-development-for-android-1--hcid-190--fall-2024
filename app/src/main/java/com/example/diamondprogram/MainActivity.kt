package com.example.diamondprogram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diamondprogram.ui.theme.DiamondprogramTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiamondprogramTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiamondApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DiamondApp(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf(TextFieldValue("")) }
    var diamondOutput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Enter a positive number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val number = input.text.toIntOrNull()
            diamondOutput = if (number != null && number > 0) {
                generateDiamond(number)
            } else {
                "Please enter a valid positive number."
            }
        }) {
            Text("Generate Diamond")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = diamondOutput, modifier = Modifier.fillMaxWidth())
    }
}

fun generateDiamond(number: Int): String {
    val stringBuilder = StringBuilder()

    // Ensure the number is even
    if (number % 2 == 0) {
        // Upper part of the diamond
        for (i in 0 until number / 2) {
            val spaceCount = number / 2 - i - 1
            val starCount = number - 2 * spaceCount

            stringBuilder.append(" ".repeat(spaceCount))
            stringBuilder.append("*".repeat(starCount))
            stringBuilder.append("\n")
        }

        // Lower part of the diamond
        for (i in 0 until number / 2) {
            val spaceCount = i
            val starCount = number - 2 * spaceCount

            stringBuilder.append(" ".repeat(spaceCount))
            stringBuilder.append("*".repeat(starCount))
            stringBuilder.append("\n")
        }
    } else {
        // Handle odd numbers if needed (optional)
        for (i in 0 until number / 2 + 1) {
            val spaceCount = number / 2 - i
            val starCount = 2 * i + 1

            stringBuilder.append(" ".repeat(spaceCount))
            stringBuilder.append("*".repeat(starCount))
            stringBuilder.append("\n")
        }

        for (i in 0 until number / 2) {
            val spaceCount = i + 1
            val starCount = number - 2 * (i + 1)

            stringBuilder.append(" ".repeat(spaceCount))
            stringBuilder.append("*".repeat(starCount))
            stringBuilder.append("\n")
        }
    }

    return stringBuilder.toString()
}

@Preview(showBackground = true)
@Composable
fun DiamondAppPreview() {
    DiamondprogramTheme {
        DiamondApp()
    }
}