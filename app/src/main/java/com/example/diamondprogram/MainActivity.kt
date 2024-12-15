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
                diamondGen(number.toString())
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
fun diamondGen (input: String): String {
    val diamond = input.toIntOrNull() ?: return "Invalid input"
    if (diamond <= 0) return "Please enter a positive number"

    val diamondBuilder = StringBuilder()
    val evenNum = diamond % 2 == 0

    if (evenNum) {
        // Top single star
        val topSpace = (diamond / 2) - 1
        diamondBuilder.append("  ".repeat(topSpace)).append("  *").appendLine()

        // Top half of the diamond
        for (i in 1..(diamond / 2)) {
            val spaces = (diamond/ 2) - i
            val stars = 2 * (i + 1) - 2

            diamondBuilder.append("  ".repeat(spaces))
            diamondBuilder.append(" *".repeat(stars)).appendLine()
        }

        // Bottom half of the diamond
        for (i in 0 until (diamond / 2) - 1) {
            val spaces = i + 1
            val stars = diamond - 2 * (i + 1)

            diamondBuilder.append("  ".repeat(spaces))
            diamondBuilder.append(" *".repeat(stars)).appendLine()
        }

        // Bottom single star
        diamondBuilder.append("  ".repeat(topSpace)).append("  *").appendLine()
    } else {
        // Top half of the diamond
        for (i in 0..(diamond / 2)) {
            val spaces = (diamond / 2) - i
            val stars = 2 * i + 1

            diamondBuilder.append(" ".repeat(spaces))
            diamondBuilder.append("* ".repeat(stars).trimEnd()).appendLine()
        }

        // Bottom half of the diamond
        for (i in (diamond / 2) - 1 downTo 0) {
            val spaces = (diamond / 2) - i
            val stars = 2 * i + 1

            diamondBuilder.append(" ".repeat(spaces))
            diamondBuilder.append("* ".repeat(stars).trimEnd()).appendLine()
        }
    }

    return diamondBuilder.toString().trimEnd()
}
