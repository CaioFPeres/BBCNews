package com.example.bbcnews.ui.authentication

sealed class BiometricsState {
    object Idle : BiometricsState()  // Initial state, waiting for user action
    object NotAvailable : BiometricsState()  // Initial state, waiting for user action
    object Success : BiometricsState()  // Authentication successful
    data class Error(val message: String) : BiometricsState()  // Error occurred
    object Failed : BiometricsState()  // Authentication failed
}