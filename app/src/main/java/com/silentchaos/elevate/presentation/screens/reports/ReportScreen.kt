package com.silentchaos.elevate.presentation.screens.reports

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze

@Composable
fun ReportScreen(navHost: NavHostController){
    val options = listOf("Monthly", "Weekly")
    Scaffold(
        topBar = {
            ReportTopBar(options)
        }
    ) { innerPadding->

    }
}



@Composable
fun ReportTopBar(options: List<String> = listOf("Option 1", "Option 2")){
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
                text = "Performance Reports",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 4.dp)

            )
            Text(
                text = "Track your progress & earnings",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 16.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))

            SlidingSwitch(options)

        }


    }
}



@Composable
fun SlidingSwitch(
    options: List<String> = listOf("Option 1", "Option 2")
) {
    var selectedIndex by remember { mutableStateOf(0) }

    val animatedOffset by animateDpAsState(
        targetValue = if (selectedIndex == 0) 0.dp else 100.dp, // width of each option
        label = "SlideAnimation"
    )
    val hazeState = remember { HazeState() }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .width(210.dp)   // total width
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .haze(
                state = hazeState, // basic frosted glass
                style = HazeStyle(
                    blurRadius = HazeDefaults.blurRadius,
                    tint = HazeDefaults.tint(Color.Black)
                )
            )

            .background(Color.Black.copy(alpha = 0.2f)) // translucent layer
            //.background(Color.LightGray), // background of switch
    ) {
        // Sliding Highlight
        Box(
            modifier = Modifier
                .padding(4.dp)
                .offset(x = animatedOffset)
                .width(100.dp) // width = half of total
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp))
                .haze(
                    state = hazeState, // basic frosted glass
                    style = HazeStyle(
                        blurRadius = HazeDefaults.blurRadius,
                        tint = HazeDefaults.tint(Color.Blue)
                    )
                )
                .background(Color.Blue.copy(alpha = 0.5f))

        )

        // Options
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEachIndexed { index, text ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                        .fillMaxHeight() // whole section clickable
                        .clickable{ selectedIndex = index },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        color = if (selectedIndex == index) Color.White else Color.White.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SwitchPreview(){
    val options = listOf("Monthly", "Weekly")
    SlidingSwitch(options)
}
