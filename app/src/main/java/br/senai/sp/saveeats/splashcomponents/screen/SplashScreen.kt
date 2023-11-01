package br.senai.sp.saveeats.splashcomponents.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import br.senai.sp.saveeats.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {

        scale.animateTo(
            targetValue = .5f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }
            )
        )
        delay(1000L)
        navController.navigate("first_presentation_screen")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(76,132,62)
    ) {

        Image(
            modifier = Modifier
                .scale(scale.value),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
        )

    }

}