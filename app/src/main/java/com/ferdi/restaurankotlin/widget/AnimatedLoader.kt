package com.ferdi.restaurankotlin.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ferdi.restaurankotlin.R

@Composable
fun AnimatedLoader(modifier: Modifier = Modifier) {
    val loaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.animation_loading
        )
    )

    val loaderProgress by animateLottieCompositionAsState(
        loaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = loaderLottieComposition,
        progress =   loaderProgress,
        modifier = modifier
    )
}