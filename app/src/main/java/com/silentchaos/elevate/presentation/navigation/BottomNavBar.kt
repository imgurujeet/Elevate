package com.silentchaos.elevate.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.silentchaos.elevate.R
import com.silentchaos.elevate.ui.theme.bottomNavItem

@Composable
fun BottomNavBar(navHost: NavHostController){
    var selectedScreen by remember { mutableStateOf(0) }
    val haptic = LocalHapticFeedback.current

    val items = listOf(
        Screen.HomeScreen,
        Screen.ReportScreen,
        Screen.ProfileScreen
    )
    val navBackStackEntry by navHost.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(navBackStackEntry) {
        val currentDes = navBackStackEntry?.destination?.route
        selectedScreen = items.indexOfFirst { currentDes==it.route }
        if (selectedScreen<0) selectedScreen=0
    }



    Box(
        modifier= Modifier
            .navigationBarsPadding()
            .height(80.dp)
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {

        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (screen in items) {
                val isSelected = screen == items[selectedScreen]
                val animatedWeight by animateFloatAsState(targetValue = if (isSelected) 1.2f else 1f)
                Box(
                    modifier = Modifier.weight(animatedWeight),
                    contentAlignment = Alignment.Center,
                ) {
                    val interactionSource = remember { MutableInteractionSource() }

                  BottomNavItem(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
//                            selectedScreen = screens.indexOf(screen)
                            navHost.navigate(screen.route) {
                                popUpTo(Screen.HomeScreen.route) {
                                    inclusive = false
                                }
                                launchSingleTop = true
                            }
                        },
                        item = screen,
                        isSelected = isSelected
                    )
                }
            }
        }
    }
//    NavigationBar {
//        items.forEach { screen ->
//            NavigationBarItem(
//                icon ={
//                    screen.icon?.let {iconRes->
//                        Icon(
//                            painter= painterResource(iconRes),
//                            contentDescription = screen.title,
//                            modifier = Modifier.size(24.dp)
//
//                        )
//
//                    }
//                },
//                label = {Text(screen.title?:"")},
//                selected = currentRoute==screen.route,
//                onClick = {
//                    if (currentRoute != screen.route) {
//                        navHost.navigate(screen.route) {
//                            popUpTo(navHost.graph.startDestinationId) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                }
//            )
//
//        }
//
//    }
}


@Composable
private fun BottomNavItem(
    modifier: Modifier = Modifier,
    item: Screen,
    isSelected: Boolean,
) {

    val animatedHeight by animateDpAsState(targetValue = if (isSelected) 50.dp else 26.dp)
    val animatedElevation by animateDpAsState(targetValue = if (isSelected) 10.dp else 0.dp)
    val animatedAlpha by animateFloatAsState(targetValue = if (isSelected) 1f else .5f)
    val animatedIconSize by animateDpAsState(
        targetValue = if (isSelected) 26.dp else 24.dp,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .height(animatedHeight)
                .shadow(
                    elevation = animatedElevation,
                    shape = RoundedCornerShape(10.dp),
                  //  ambientColor = Color.White,
                    spotColor = Color(0xFF3653FF)
                )
                .background(
                    color = if(isSelected) bottomNavItem else Color.Transparent,  // customize
                    shape = RoundedCornerShape(40.dp),
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            item.icon?.let { iconRes ->
                FlipIcon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight()
                        .padding(start = 11.dp)
                        .alpha(animatedAlpha)
                        .size(animatedIconSize),
                    isActive = isSelected,
                    activeIcon = painterResource(id = item.activeIcon ?: iconRes),
                    inactiveIcon = painterResource(id = iconRes),
                    contentDescription = item.title?:""
                )
            }



            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = item.title ?: "",
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp, end = 10.dp),
                    maxLines = 1,
                )
            }

        }
    }
}


@Composable
fun FlipIcon(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeIcon: Painter,
    inactiveIcon: Painter,
    contentDescription: String,
)
{
    val animationRotation by animateFloatAsState(
        targetValue = if (isActive) 180f else 0f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )
    Box(
        modifier = modifier
            .graphicsLayer { rotationY = animationRotation },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter =  if (animationRotation > 90f) activeIcon else inactiveIcon,
            contentDescription = contentDescription,
            tint = Color.White
        )
    }
}
