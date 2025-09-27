package com.silentchaos.elevate.presentation.navigation

import com.silentchaos.elevate.R

sealed class Screen (val route: String,val title: String? = null,val icon: Int?=null,val activeIcon: Int?=null) {
    data object SplashScreen : Screen("splash_screen")
    data object HomeScreen : Screen(route = "home","Home", R.drawable.ic_home,R.drawable.ic_home_active)
    data object ReportScreen : Screen (route = "report", "Reports" ,R.drawable.ic_report,R.drawable.ic_report_active )
    data object ProfileScreen : Screen(route = "profile","Profile",R.drawable.ic_profile,R.drawable.ic_profile_active)
}