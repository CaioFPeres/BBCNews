package com.example.bbcnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bbcnews.ui.mainScreen.MainScreen
import com.example.bbcnews.data.repository.NewsRepositoryImpl
import com.example.bbcnews.ui.mainScreen.MainScreenViewModel
import com.example.bbcnews.ui.newsScreen.NewsScreen
import com.example.bbcnews.ui.theme.BBCNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = NewsRepositoryImpl()
        val viewModel = MainScreenViewModel(repository)

        enableEdgeToEdge()
        setContent {
            BBCNewsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "MainScreen") {
                    composable("MainScreen") {
                        MainScreen(navController, viewModel)
                    }
                    composable(route = "NewsScreen") {
                        NewsScreen(navController, viewModel)
                    }
                }
            }
        }
    }
}