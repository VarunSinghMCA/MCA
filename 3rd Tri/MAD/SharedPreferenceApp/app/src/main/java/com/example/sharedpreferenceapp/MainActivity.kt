package com.example.sharedpreferenceapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicalAppUI()
        }
    }
}

@Composable
fun MedicalAppUI() {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("PatientData", Context.MODE_PRIVATE)
    var name by remember { mutableStateOf("") }
    var patientId by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var isDiabetic by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Patient Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = patientId,
            onValueChange = { patientId = it },
            label = { Text("Patient ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = bloodGroup,
            onValueChange = { bloodGroup = it },
            label = { Text("Blood Group") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Diabetic Patient")
            Switch(
                checked = isDiabetic,
                onCheckedChange = { isDiabetic = it }
            )
        }

        Button(
            onClick = {
                val editor = sharedPref.edit()
                val currentDate = SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
                ).format(Date())
                editor.putString("name", name)
                editor.putString("patientId", patientId)
                editor.putString("bloodGroup", bloodGroup)
                editor.putBoolean("diabetes", isDiabetic)
                editor.putString("lastVisit", currentDate)
                editor.apply()
                Toast.makeText(
                    context,
                    "Patient Data Saved",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        Button(
            onClick = {
                name = sharedPref.getString("name", "") ?: ""
                patientId = sharedPref.getString("patientId", "") ?: ""
                bloodGroup = sharedPref.getString("bloodGroup", "") ?: ""
                isDiabetic = sharedPref.getBoolean("diabetes", false)
                val lastVisit = sharedPref.getString("lastVisit", "No Record")
                Toast.makeText(
                    context,
                    "Last Visit: $lastVisit",
                    Toast.LENGTH_LONG
                ).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load")
        }
    }
}