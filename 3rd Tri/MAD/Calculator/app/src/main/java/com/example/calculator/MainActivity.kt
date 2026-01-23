package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    private var firstNumber = 0.0
    private var operator = ""
    private var isNewInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
    }

    fun onDigitClick(view: View) {
        val button = view as Button

        if (isNewInput) {
            tvResult.text = ""
            isNewInput = false
        }

        tvResult.append(button.text)
    }

    fun onOperatorClick(view: View) {
        val button = view as Button

        firstNumber = tvResult.text.toString().toDoubleOrNull() ?: 0.0
        operator = button.text.toString()
        isNewInput = true
    }

    fun onEqualClick(view: View) {
        val secondNumber = tvResult.text.toString().toDoubleOrNull() ?: 0.0

        val result = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else 0.0
            else -> 0.0
        }

        tvResult.text = result.toString()
        isNewInput = true
    }

    fun onClearClick(view: View) {
        tvResult.text = "0"
        firstNumber = 0.0
        operator = ""
        isNewInput = true
    }
}
