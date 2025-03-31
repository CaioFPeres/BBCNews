package com.example.bbcnews

import AuthenticateUseCase
import BiometricScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bbcnews.ui.mainScreen.MainScreen
import com.example.bbcnews.data.repository.NewsRepositoryImpl
import com.example.bbcnews.ui.authentication.BiometricsViewModel
import com.example.bbcnews.ui.mainScreen.MainScreenViewModel
import com.example.bbcnews.ui.newsScreen.NewsScreen
import com.example.bbcnews.ui.newsScreen.NewsScreenViewModel
import com.example.bbcnews.ui.theme.BBCNewsTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = NewsRepositoryImpl()
        val newsScreenViewModel = NewsScreenViewModel()
        val mainScreenViewModel = MainScreenViewModel(repository)
        val biometricsViewModel = BiometricsViewModel(AuthenticateUseCase(this))

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