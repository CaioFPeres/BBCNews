package com.example.bbcnews

import BiometricScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bbcnews.ui.authentication.BiometricsViewModel
import com.example.bbcnews.ui.mainScreen.MainScreen
import com.example.bbcnews.ui.mainScreen.MainScreenViewModel
import com.example.bbcnews.ui.newsScreen.NewsScreen
import com.example.bbcnews.ui.newsScreen.NewsScreenViewModel
import com.example.bbcnews.ui.theme.BBCNewsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : FragmentActivity() {
    private val biometricsViewModel: BiometricsViewModel by viewModel { parametersOf(this) }
    private val mainScreenViewModel: MainScreenViewModel by viewModel()
    private val newsScreenViewModel: NewsScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            BBCNewsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "BiometricsAuth") {
                    composable("BiometricsAuth") {
                        BiometricScreen(navController, biometricsViewModel)
                    }
                    composable("MainScreen") {
                        MainScreen(navController, mainScreenViewModel, newsScreenViewModel)
                    }
                    composable(route = "NewsScreen") {
                        NewsScreen(navController, newsScreenViewModel)
                    }
                }
            }
        }
    }
}