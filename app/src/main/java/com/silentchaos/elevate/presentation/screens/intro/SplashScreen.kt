package com.silentchaos.elevate.presentation.screens.intro

import androidx.compose.runtime.Composable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val visible = remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
////            painter = painterResource(id = R.drawable.splash_bg),
////            contentDescription = null,
////            contentScale = ContentScale.Crop,
////            modifier = Modifier.fillMaxSize()
////        )

        // Single line shimmer text with fade
        AnimatedVisibility(
            visible = visible.value,
            enter = fadeIn(tween(1200)),
            exit = fadeOut(tween(800)),
            modifier = Modifier.align(Alignment.Center)
        ) {
            ShimmeringText(
                text = "Let's Walk Together",
                shimmerColor = Color.White,
                fontSize = 34.sp, // reduced so it fits
                fontWeight = FontWeight.Medium
            )
        }
    }

    // Timeout navigation
    LaunchedEffect(Unit) {
        delay(2000)
        visible.value = false
        delay(500)
        onTimeout()
    }
}

@Composable
private fun ShimmeringText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    // fontFamily: FontFamily,
    fontWeight: FontWeight,
    shimmerColor: Color,
    textStyle: TextStyle = LocalTextStyle.current,
    animationSpec: DurationBasedAnimationSpec<Float> = tween(2000, 300, LinearEasing)
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ShimmeringTextTransition")

    val shimmerProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animationSpec),
        label = "ShimmerProgress"
    )

    val brush = remember(shimmerProgress) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val initialXOffset = -size.width
                val totalSweepDistance = size.width * 2
                val currentPosition = initialXOffset + totalSweepDistance * shimmerProgress

                return LinearGradientShader(
                    colors = listOf(Color.Transparent, shimmerColor, Color.Transparent),
                    from = Offset(currentPosition, 0f),
                    to = Offset(currentPosition + size.width, 0f)
                )
            }
        }
    }

    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        // fontFamily = fontFamily,
        fontWeight = fontWeight,
        style = textStyle.copy(brush = brush)
    )
}
