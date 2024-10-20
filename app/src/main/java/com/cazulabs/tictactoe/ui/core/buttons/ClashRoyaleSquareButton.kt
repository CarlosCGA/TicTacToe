package com.cazulabs.tictactoe.ui.core.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cazulabs.tictactoe.ui.core.OutlinedText
import com.cazulabs.tictactoe.ui.core.animations.onPressAnimation
import com.cazulabs.tictactoe.ui.theme.RedCloseDialogButtonBackground
import com.cazulabs.tictactoe.ui.theme.RedCloseDialogButtonBorderDark
import com.cazulabs.tictactoe.ui.theme.RedCloseDialogButtonBorderLight
import com.cazulabs.tictactoe.ui.theme.RedCloseDialogButtonHalfBottom
import com.cazulabs.tictactoe.ui.theme.RedCloseDialogButtonHalfTop

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClashRoyaleSquareButton(
    modifier: Modifier = Modifier,
    defaultSize: Float = 30F,
    borderDark: Color,
    borderLight: Color,
    background: Color,
    foregroundLight: Color,
    foregroundDark: Color,
    text: String,
    onClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val cornerRadius = 6.dp

    var targetSize by rememberSaveable {
        mutableFloatStateOf(defaultSize)
    }

    val size by animateFloatAsState(targetValue = targetSize, label = "animate info button size")

    //Black border
    Box(
        modifier = modifier
            .size(size.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(cornerRadius))
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        onPressAnimation(
                            pressGestureScope = this,
                            size = targetSize,
                            onNewSize = { newSize -> targetSize = newSize },
                            coroutineScope = coroutineScope,
                            onReleased = onClick
                        )
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        val borderBrush = Brush.verticalGradient(
            colors = listOf(
                borderLight,
                borderDark
            )
        )
        //Color3
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(borderBrush, RoundedCornerShape(cornerRadius))
        ) {
            //Color2
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp, top = 2.dp)
                    .background(background, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 1.dp, bottom = 2.dp, start = 2.dp, end = 1.dp),
                ) {
                    val cornerRadius1 = 8.dp
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)
                            .background(
                                foregroundLight,
                                RoundedCornerShape(
                                    topStart = cornerRadius1,
                                    topEnd = cornerRadius1,
                                    bottomStart = 0.dp,
                                    bottomEnd = 0.dp,
                                )
                            )
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)
                            .background(
                                foregroundDark,
                                RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 0.dp,
                                    bottomStart = cornerRadius1,
                                    bottomEnd = cornerRadius1
                                )
                            )
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    OutlinedText(text = text, fontSize = 12.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CloseButtonPreview() {
    ClashRoyaleSquareButton(
        borderDark = RedCloseDialogButtonBorderDark,
        borderLight = RedCloseDialogButtonBorderLight,
        background = RedCloseDialogButtonBackground,
        foregroundDark = RedCloseDialogButtonHalfTop,
        foregroundLight = RedCloseDialogButtonHalfBottom,
        text = "X",
        onClick = {}
    )
}