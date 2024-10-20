package com.cazulabs.tictactoe.ui.core.animations

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.PressGestureScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

private const val ANIMATION_DURATION = 100
private const val SIZE_DELTA = 5

suspend fun onPressAnimation(
    pressGestureScope: PressGestureScope,
    size: Float,
    onNewSize: (Float) -> Unit,
    coroutineScope: CoroutineScope,
    onPressed: () -> Unit = {},
    onReleased: () -> Unit = {},
    onCanceled: () -> Unit = {}
): Job {
    return coroutineScope.launch {
        animateSmall(coroutineScope, size, onNewSize)()
        onPressed()

        val released = try {
            pressGestureScope.tryAwaitRelease()
        } catch (c: CancellationException) {
            false
        }
        animateBig(coroutineScope, size, onNewSize)()
        if (released)
            onReleased()
        else
            onCanceled()
    }
}

suspend fun animateSmall(
    animationRoutine: CoroutineScope,
    size: Float,
    onNewSize: (Float) -> Unit
): () -> Unit {
    val pressSize = size - SIZE_DELTA

    return {
        animationRoutine.launch {
            coroutineScope {
                launch {
                    animate(
                        size,
                        pressSize,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewSize(value) }
                    )
                }
            }
        }
    }
}

suspend fun animateBig(
    animationRoutine: CoroutineScope,
    size: Float,
    onNewSize: (Float) -> Unit
): () -> Unit {
    val pressSize = size - SIZE_DELTA

    return {
        animationRoutine.launch {
            coroutineScope {
                launch {
                    animate(
                        pressSize,
                        size,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewSize(value) }
                    )
                }
            }
        }
    }
}