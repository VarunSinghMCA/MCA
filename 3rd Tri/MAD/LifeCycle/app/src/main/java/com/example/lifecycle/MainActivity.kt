package com.example.lifecycle

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

private const val TAG = "MainActivity"
private const val LOG_TAG = "MyLogs"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate called")
        Log.d(LOG_TAG, "onCreate called")
        readLog()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
        Log.d(LOG_TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
        Log.d(LOG_TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
        Log.d(LOG_TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
        Log.d(LOG_TAG, "onStop called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart called")
        Log.d(LOG_TAG, "onRestart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
        Log.d(LOG_TAG, "onDestroy called")
    }

    @SuppressLint("SetTextI18n")
    private fun readLog() {
        val logs = StringBuilder()
        try {
            val process = Runtime.getRuntime().exec("logcat -d -s $LOG_TAG")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String? = reader.readLine()
            while (line != null) {
                logs.append(line).append("\n")
                line = reader.readLine()
            }
        } catch (e: IOException) {
            Log.e(TAG, "Error reading log: $e")
        }
        val logView: TextView = findViewById(R.id.logView)
        logView.text = logs.toString()
    }
}
