package com.example.bbcnews.ui.biometricsScreen

import usecase.AuthenticateUseCase
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BiometricsViewModel(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<BiometricsState>(BiometricsState.Idle)
    val authState: StateFlow<BiometricsState> = _authState

    init {
        if(!authenticateUseCase.isBiometricAvailable())
            _authState.value = BiometricsState.NotAvailable
    }

    fun launchBiometricPrompt() {
        if(authState.value != BiometricsState.Success)
            authenticateUseCase.showBiometricPrompt(
                onSuccess = { onAuthenticationResult(true) },
                onError = { error -> onAuthenticationResult(false, error) }
            )
    }

    fun onAuthenticationResult(success: Boolean, errorMessage: String? = null) {
        _authState.value = if (success) BiometricsState.Success
        else BiometricsState.Error(errorMessage ?: "Unknown error")
    }
}