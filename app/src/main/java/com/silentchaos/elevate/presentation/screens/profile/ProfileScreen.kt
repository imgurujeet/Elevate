package com.silentchaos.elevate.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navHost: NavHostController){
    Scaffold(
        topBar = {
            ProfileTopBar()
        }
    ) { innerPadding->

    }
}



@Composable
fun ProfileTopBar(){
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF5400FF),Color(0xFF3718F5), ), // change with your colors
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY,0f)
                ),
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Settings",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(vertical = 16.dp)

            )
            Spacer(modifier = Modifier.height(24.dp))
//            Text(
//                text = "Track your daily claims",
//                color = Color.White,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Normal,
//                modifier = Modifier.padding(bottom = 16.dp)
//
//            )

        }


    }
}