package com.cazulabs.tictactoe.ui.core.animations

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.PressGestureScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

private const val ANIMATION_DURATION = 25
private const val INITIAL_VELOCITY = 50F
private const val SCALE_DELTA = 0.075F

suspend fun onPressAnimation(
    pressGestureScope: PressGestureScope,
    scale: Float,
    onNewScale: (Float) -> Unit,
    coroutineScope: CoroutineScope,
    onPressed: () -> Unit = {},
    onReleased: () -> Unit = {},
    onCanceled: () -> Unit = {}
): Job {
    return coroutineScope.launch {
        async { animateSmall(coroutineScope, scale, onNewScale)() }.await()
        onPressed()

        val released = try {
            pressGestureScope.tryAwaitRelease()
        } catch (c: CancellationException) {
            false
        }
        async { animateBig(coroutineScope, scale, onNewScale)() }.await()
        if (released)
            onReleased()
        else
            onCanceled()
    }
}

suspend fun animateSmall(
    animationRoutine: CoroutineScope,
    scale: Float,
    onNewScale: (Float) -> Unit
): () -> Unit {
    val pressSize = scale - SCALE_DELTA

    return {
        animationRoutine.launch {
            coroutineScope {
                launch {
                    animate(
                        initialValue = scale,
                        targetValue = pressSize,
                        initialVelocity = INITIAL_VELOCITY,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewScale(value) }
                    )
                }
            }
        }
    }
}

suspend fun animateBig(
    animationRoutine: CoroutineScope,
    scale: Float,
    onNewScale: (Float) -> Unit
): () -> Unit {
    val pressSize = scale - SCALE_DELTA

    return {
        animationRoutine.launch {
            coroutineScope {
                launch {
                    animate(
                        initialValue = pressSize,
                        targetValue = scale,
                        initialVelocity = INITIAL_VELOCITY,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewScale(value) }
                    )
                }
            }
        }
    }
}