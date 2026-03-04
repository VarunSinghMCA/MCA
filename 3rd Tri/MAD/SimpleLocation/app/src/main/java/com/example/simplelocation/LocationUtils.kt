package com.example.simplelocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.util.Locale

class LocationUtils(private val context: Context) {
    
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    
    /**
     * Check if location permissions are granted
     */
    fun hasLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    /**
     * Request location updates from the FusedLocationProviderClient
     */
    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(viewModel: LocationViewModel) {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let { location ->
                    val locationData = LocationData(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )
                    viewModel.updateLocation(locationData, this@LocationUtils)
                }
            }
        }
        
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10000L // 10 seconds
        ).build()
        
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }
    
    /**
     * Convert latitude and longitude to a readable address using Geocoder
     */
    fun reverseGeocodeLocation(location: LocationData): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        
        return try {
            @Suppress("DEPRECATION")
            val addresses: MutableList<Address>? = geocoder.getFromLocation(
                location.latitude,
                location.longitude,
                1
            )
            
            if (addresses?.isNotEmpty() == true) {
                val address = addresses[0]
                buildString {
                    append(address.getAddressLine(0) ?: "")
                    if (address.locality != null) {
                        append("\n${address.locality}")
                    }
                    if (address.adminArea != null) {
                        append(", ${address.adminArea}")
                    }
                    if (address.countryName != null) {
                        append("\n${address.countryName}")
                    }
                }
            } else {
                "Address not found"
            }
        } catch (e: Exception) {
            "Unable to fetch address: ${e.message}"
        }
    }
}