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

suspend fun onPressAnimationWithBounce(
    pressGestureScope: PressGestureScope,
    size: Float,
    onNewSize: (Float) -> Unit,
    coroutineScope: CoroutineScope,
    onPressed: () -> Unit = {},
    onReleased: () -> Unit = {},
    onCanceled: () -> Unit = {}
): Job {
    return coroutineScope.launch {
        animateSmallWithBounce(coroutineScope, size, onNewSize)()
        onPressed()

        val released = try {
            pressGestureScope.tryAwaitRelease()
        } catch (c: CancellationException) {
            false
        }
        animateBigWithBounce(coroutineScope, size, onNewSize)()
        if (released)
            onReleased()
        else
            onCanceled()
    }
}

suspend fun animateSmallWithBounce(
    animationRoutine: CoroutineScope,
    size: Float,
    onNewSize: (Float) -> Unit
): () -> Unit {
    val pressSize = (size - (size * 0.15)).toFloat()
    val delta1 = (size * 0.075).toFloat()
    val delta2 = (size * 0.05).toFloat()
    val delta3 = (size * 0.025).toFloat()
    return {
        animationRoutine.launch {
            coroutineScope {
                launch {
                    animate(
                        size,
                        pressSize - delta1,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewSize(value) }
                    )
                    animate(
                        pressSize - delta1,
                        pressSize + delta2,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewSize(value) }
                    )
                    animate(
                        pressSize + delta2,
                        pressSize - delta3,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewSize(value) }
                    )
                    animate(
                        pressSize - delta3,
                        pressSize,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewSize(value) }
                    )
                }
            }
        }
    }
}

suspend fun animateBigWithBounce(
    animationRoutine: CoroutineScope,
    size: Float,
    onNewSize: (Float) -> Unit
): () -> Unit {
    val pressSize = (size - (size * 0.15)).toFloat()
    val delta1 = (size * 0.075).toFloat()
    val delta2 = (size * 0.05).toFloat()
    val delta3 = (size * 0.025).toFloat()
    return {
        animationRoutine.launch {
            coroutineScope {
                launch {
                    animate(
                        pressSize,
                        size + delta1,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewSize(value) }
                    )
                    animate(
                        size + delta1,
                        size - delta2,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewSize(value) }
                    )
                    animate(
                        size - delta2,
                        size + delta3,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewSize(value) }
                    )
                    animate(
                        size + delta3,
                        size,
                        animationSpec = tween(ANIMATION_DURATION),
                        block = { value, _ -> onNewSize(value) }
                    )
                }
            }
        }
    }
}