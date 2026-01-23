package com.example.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverterScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UnitConverterScreen(modifier: Modifier = Modifier) {

    var inputValue by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val units = listOf("Meters", "Centimeters", "Kilometers")

    var fromUnit by remember { mutableStateOf(units[0]) }
    var toUnit by remember { mutableStateOf(units[1]) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineMedium)

        // Input Field
        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text("Enter value") },
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdowns Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            UnitDropdown(
                label = "From",
                units = units,
                selectedUnit = fromUnit,
                onUnitSelected = { fromUnit = it },
                modifier = Modifier.weight(1f)
            )

            UnitDropdown(
                label = "To",
                units = units,
                selectedUnit = toUnit,
                onUnitSelected = { toUnit = it },
                modifier = Modifier.weight(1f)
            )
        }

        // Convert Button
        Button(
            onClick = {
                val value = inputValue.toDoubleOrNull()
                if (value != null) {
                    result = convertUnits(value, fromUnit, toUnit).toString()
                } else {
                    result = "Invalid input"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convert")
        }

        // Result
        Text(
            text = "Result: $result",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun UnitDropdown(
    label: String,
    units: List<String>,
    selectedUnit: String,
    onUnitSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("$label: $selectedUnit")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            units.forEach { unit ->
                DropdownMenuItem(
                    text = { Text(unit) },
                    onClick = {
                        onUnitSelected(unit)
                        expanded = false
                    }
                )
            }
        }
    }
}

// Conversion Logic
fun convertUnits(value: Double, from: String, to: String): Double {
    val valueInMeters = when (from) {
        "Meters" -> value
        "Centimeters" -> value / 100
        "Kilometers" -> value * 1000
        else -> value
    }

    return when (to) {
        "Meters" -> valueInMeters
        "Centimeters" -> valueInMeters * 100
        "Kilometers" -> valueInMeters / 1000
        else -> valueInMeters
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConvertorTheme {
        UnitConverterScreen()
    }
}
