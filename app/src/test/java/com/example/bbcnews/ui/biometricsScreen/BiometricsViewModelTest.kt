package com.example.bbcnews.ui.biometricsScreen

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import usecase.AuthenticateUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class BiometricsViewModelTest {
    private lateinit var viewModel: BiometricsViewModel
    private lateinit var authenticateUseCase: AuthenticateUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        authenticateUseCase = mockk(relaxed = true)

        every { authenticateUseCase.isBiometricAvailable() } returns true
        viewModel = BiometricsViewModel(authenticateUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when biometric is available should start in idle state`() {
        assertEquals(BiometricsState.Idle, viewModel.authState.value)
    }

    @Test
    fun `when biometric is not available should set not available state`() {
        every { authenticateUseCase.isBiometricAvailable() } returns false
        viewModel = BiometricsViewModel(authenticateUseCase)

        assertEquals(BiometricsState.NotAvailable, viewModel.authState.value)
    }

    @Test
    fun `when authentication succeeds should set success state`() {
        viewModel.onAuthenticationResult(true)

        assertEquals(BiometricsState.Success, viewModel.authState.value)
    }

    @Test
    fun `when authentication fails should set error state`() {
        val errorMessage = "Authentication failed"
        viewModel.onAuthenticationResult(false, errorMessage)

        assertEquals(BiometricsState.Error(errorMessage), viewModel.authState.value)
    }

    @Test
    fun `when launchBiometricPrompt called should call useCase`() {
        viewModel.launchBiometricPrompt()

        verify { authenticateUseCase.showBiometricPrompt(any(), any()) }
    }
}
