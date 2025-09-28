package com.silentchaos.elevate.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navHost: NavHostController){
    Scaffold(
        topBar = {
            HomeTopBar()
        }
    ){ innerPadding ->

    }
}

@Composable
fun HomeTopBar(){
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF001BCE), Color(0xFF5400FF)), // change with your colors
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY,0f) // vertical gradient
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
                text = "Today's Production",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 4.dp)

            )
            Text(
                text = "Track your daily claims",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 16.dp)

            )

        }


    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeTopBarPreview(){
    HomeTopBar()
}