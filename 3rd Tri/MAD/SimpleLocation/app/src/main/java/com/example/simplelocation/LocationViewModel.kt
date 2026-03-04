package com.example.simplelocation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _address = mutableStateOf<String?>(null)
    val address: State<String?> = _address

    fun updateLocation(newLocation: LocationData, locationUtils: LocationUtils) {
        _location.value = newLocation
        // Fetch address asynchronously when location updates
        viewModelScope.launch(Dispatchers.IO) {
            val result = locationUtils.reverseGeocodeLocation(newLocation)
            _address.value = result
        }
    }
}