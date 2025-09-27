package com.silentchaos.elevate.presentation.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.silentchaos.elevate.presentation.screens.home.HomeScreen
import com.silentchaos.elevate.presentation.screens.intro.SplashScreen
import com.silentchaos.elevate.presentation.screens.profile.ProfileScreen
import com.silentchaos.elevate.presentation.screens.reports.ReportScreen


@Composable
fun AppNavigation(navController: NavHostController){

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Scaffold(

        bottomBar = {
            if (currentRoute in listOf(
                    Screen.HomeScreen.route,
                    Screen.ReportScreen.route,
                    Screen.ProfileScreen.route
                )
            ) {
                BottomNavBar(navController)
            }

        }
    ){ innerPadding ->

        NavHost(navController=navController, startDestination=Screen.SplashScreen.route){
            //Splash Screen
            composable(Screen.SplashScreen.route){
                SplashScreen(
                    onTimeout = {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.SplashScreen.route) { inclusive = true }
                        }
                    }
                )

            }

            //HomeScreen
            composable(Screen.HomeScreen.route){
                HomeScreen(navController)
            }
            //profile Screen
            composable(Screen.ProfileScreen.route) {
                ProfileScreen(navController)
            }
            //ReportScreen
            composable (Screen.ReportScreen.route) {
                ReportScreen(navController)
            }

        }

    }


}


