package com.ferdi.restaurankotlin.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ferdi.restaurankotlin.R

@Composable
fun AnimatedLoader(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.animation_loading)
    )

    val isTestEnv = LocalInspectionMode.current

    // Gunakan progress manual saat test untuk kontrol kecepatan animasi
    val progress by if (isTestEnv) {
        // Pakai progress yang tetap atau lambat saat test
        remember { mutableStateOf(0.2f) } // atau static value
    } else {
        // Normal animation di runtime
        animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true
        )
    }

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier
    )
}
