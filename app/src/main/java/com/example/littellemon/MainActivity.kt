package com.example.littellemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littellemon.ui.theme.LittellemonTheme

class MainActivity : ComponentActivity() {
    val sharedPreferences by lazy { getSharedPreferences("Little Lemon", MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContent {
            LittellemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationComposable(context = applicationContext,navController = navController)
                }
            }
        }
    }
}
@Composable
fun NavigationComposable(context: Context, navController: NavHostController) {

    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    var startDestination = OnboardingDestination.route

    if (sharedPreferences.getBoolean("userRegistered", false)) {
        startDestination = HomeDestination.route
    }

    NavHost(navController = navController, startDestination = startDestination){
        composable(OnboardingDestination.route){
            Onboarding(context, navController)
        }
        composable(HomeDestination.route){
            Home(navController)
        }
        composable(ProfileDestination.route){
            Profile(context, navController)
        }
    }
}

