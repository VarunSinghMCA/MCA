package com.example.unisync.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unisync.data.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@HiltViewModel
class UniversitiesViewModel @Inject constructor(
    private val repository: UniversityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UniversitiesUiState>(UniversitiesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        observeUniversities()
        refreshNow()
    }

    private fun observeUniversities() {
        viewModelScope.launch {
            combine(
                repository.observeUniversities(),
                repository.observeLastSyncTime()
            ) { universities, lastSyncTime ->
                UniversitiesUiState.Success(
                    universities = universities,
                    lastSyncTimeMillis = lastSyncTime
                )
            }.collect { successState ->
                _uiState.value = successState
            }
        }
    }

    fun refreshNow() {
        viewModelScope.launch {
            if (_uiState.value !is UniversitiesUiState.Success) {
                _uiState.value = UniversitiesUiState.Loading
            }

            runCatching {
                repository.refreshUniversities()
            }.onFailure { throwable ->
                _uiState.value = UniversitiesUiState.Error(
                    message = throwable.message ?: "Unable to sync universities."
                )
            }
        }
    }
}
