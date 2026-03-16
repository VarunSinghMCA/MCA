package com.example.unisync.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.unisync.data.repository.UniversityRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException

@HiltWorker
class UniversitySyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: UniversityRepository
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            repository.refreshUniversities()
            Result.success()
        } catch (_: IOException) {
            Result.retry()
        } catch (httpException: HttpException) {
            if (httpException.code() >= 500) {
                Result.retry()
            } else {
                Result.failure()
            }
        } catch (_: Exception) {
            Result.failure()
        }
    }
}
