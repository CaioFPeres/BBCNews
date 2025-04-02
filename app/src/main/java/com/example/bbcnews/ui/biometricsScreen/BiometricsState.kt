package com.example.bbcnews.ui.biometricsScreen

sealed class BiometricsState {
    object Idle : BiometricsState()  // Initial state, waiting for user action
    object NotAvailable : BiometricsState()  // Not available
    object Success : BiometricsState()  // Authentication successful
    data class Error(val message: String) : BiometricsState()  // Error occurred
    object Failed : BiometricsState()  // Authentication failed
}