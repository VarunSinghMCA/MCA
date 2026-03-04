package com.example.aura

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AuraApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            val habitChannel = NotificationChannel(
                HABIT_CHANNEL_ID,
                "Habit Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Daily habit reminder notifications"
            }

            val pomodoroChannel = NotificationChannel(
                POMODORO_CHANNEL_ID,
                "Pomodoro Timer",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Pomodoro focus timer notifications"
            }

            val taskChannel = NotificationChannel(
                TASK_CHANNEL_ID,
                "Task Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Task due date reminder notifications"
            }

            notificationManager.createNotificationChannels(
                listOf(habitChannel, pomodoroChannel, taskChannel)
            )
        }
    }

    companion object {
        const val HABIT_CHANNEL_ID = "aura_habit_channel"
        const val POMODORO_CHANNEL_ID = "aura_pomodoro_channel"
        const val TASK_CHANNEL_ID = "aura_task_channel"
    }
}

