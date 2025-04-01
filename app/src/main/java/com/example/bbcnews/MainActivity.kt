package com.example.bbcnews

import BiometricScreen
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bbcnews.ui.mainScreen.MainScreen
import com.example.bbcnews.ui.newsScreen.NewsScreen
import com.example.bbcnews.ui.theme.BBCNewsTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // make navigation bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = getWindow()
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

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
                        NewsScreen(navController)
                    }
                }
            }
        }
    }
}