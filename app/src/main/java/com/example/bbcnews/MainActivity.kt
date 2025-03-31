package com.example.bbcnews

import BiometricScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bbcnews.ui.mainScreen.MainScreen
import com.example.bbcnews.ui.newsScreen.NewsScreen
import com.example.bbcnews.ui.newsScreen.NewsScreenViewModel
import com.example.bbcnews.ui.theme.BBCNewsTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BBCNewsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "BiometricsAuth") {
                    composable("BiometricsAuth") {
                        BiometricScreen(navController)
                    }
                    composable("MainScreen") {
                        MainScreen(navController)
                    }
                    composable(route = "NewsScreen") {
                        NewsScreen()
                    }
                }
            }
        }
    }
}