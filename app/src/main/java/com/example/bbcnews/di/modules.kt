package com.example.bbcnews.di

import com.example.bbcnews.data.repository.NewsRepositoryImpl
import com.example.bbcnews.ui.biometricsScreen.BiometricsViewModel
import com.example.bbcnews.ui.mainScreen.MainScreenViewModel
import com.example.bbcnews.ui.newsScreen.NewsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.core.parameter.parametersOf
import androidx.fragment.app.FragmentActivity
import com.example.bbcnews.data.remote.RetrofitClient
import usecase.AuthenticateUseCase

val appModule = module {
    single { RetrofitClient("https://newsapi.org/v2/") }
    single { NewsRepositoryImpl(get()) }
    single { (activity: FragmentActivity) -> AuthenticateUseCase(activity) }
    viewModel { NewsScreenViewModel() }
    viewModel { MainScreenViewModel(get()) }
    viewModel { BiometricsViewModel(get { parametersOf(get<FragmentActivity>()) }) }
}
