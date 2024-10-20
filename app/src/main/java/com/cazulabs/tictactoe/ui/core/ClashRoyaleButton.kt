package com.cazulabs.tictactoe.ui.core

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cazulabs.tictactoe.R
import com.cazulabs.tictactoe.ui.core.animations.onPressAnimation
import com.cazulabs.tictactoe.ui.theme.Blue1
import com.cazulabs.tictactoe.ui.theme.Blue2
import com.cazulabs.tictactoe.ui.theme.Blue3
import com.cazulabs.tictactoe.ui.theme.BlueDetail
import com.cazulabs.tictactoe.ui.theme.Orange1
import com.cazulabs.tictactoe.ui.theme.Orange2
import com.cazulabs.tictactoe.ui.theme.Orange3
import com.cazulabs.tictactoe.ui.theme.OrangeDetail

const val BUTTON_DEFAULT_WIDTH = 130F
const val BUTTON_DEFAULT_HEIGHT = 65F

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClashRoyaleButton(
    modifier: Modifier = Modifier,
    defaultWidth: Float = BUTTON_DEFAULT_WIDTH,
    defaultHeight: Float = BUTTON_DEFAULT_HEIGHT,
    color3: Color,
    color2: Color,
    color1: Color,
    colorSpotShadow: Color = Color.Black,
    colorDetail: Color,
    text: String,
    onClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val cornerRadius = 6.dp

    var targetWidth by rememberSaveable { mutableFloatStateOf(defaultWidth) }
    var targetHeight by rememberSaveable { mutableFloatStateOf(defaultHeight) }

    val width by animateFloatAsState(targetValue = targetWidth, label = "animate info button size")
    val height by animateFloatAsState(
        targetValue = targetHeight,
        label = "animate info button size"
    )

    Box(
        modifier = modifier
            .width(defaultWidth.dp)
            .height(defaultHeight.dp)
    ) {
        //Black border
        Box(
            modifier = modifier
                .width(width.dp)
                .height(height.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(cornerRadius))
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            onPressAnimation(
                                pressGestureScope = this,
                                width = targetWidth,
                                height = targetHeight,
                                onNewWidth = { newSize -> targetWidth = newSize },
                                onNewHeight = { newSize -> targetHeight = newSize },
                                coroutineScope = coroutineScope,
                                onReleased = onClick
                            )
                        }

                    )
                },
            contentAlignment = Alignment.Center
        ) {
            //Blue3
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color3, RoundedCornerShape(cornerRadius))
            ) {
                //Blue2
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 4.dp)
                        .background(color2, RoundedCornerShape(cornerRadius)),
                    contentAlignment = Alignment.Center
                ) {
                    //Blue1
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color1, RoundedCornerShape(cornerRadius))
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(cornerRadius),
                                spotColor = colorSpotShadow
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                                .background(color1, RoundedCornerShape(4.dp)),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(4.dp))
                                    .offset(28.5.dp, 37.dp)
                                    .rotate(-45F),
                                contentAlignment = Alignment.TopEnd
                            ) {

                                Box {
                                    Canvas(modifier = Modifier.fillMaxSize()) {
                                        drawOval(
                                            color = colorDetail,
                                            topLeft = Offset(size.width + 2F, 5F),
                                            size = Size(10f, 15f)
                                        )
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                /*
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 4.dp),
                                    text = text,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily(Font(R.font.lilita_one_regular)),
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Clip
                                )
                                */
                                OutlinedText(
                                    text = text,
                                    outlineColor = Color.Black,
                                    fillColor = Color.White,
                                    fontFamily = FontFamily(Font(R.font.supercell_magic_regular)),
                                    outlineDrawStyle = Stroke(8F)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BattleButton() {
    ClashRoyaleButton(
        color3 = Orange3,
        color2 = Orange2,
        color1 = Orange1,
        colorSpotShadow = Color.Red,
        colorDetail = OrangeDetail,
        text = "Battle",
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun JoinButton() {
    ClashRoyaleButton(
        color3 = Blue3,
        color2 = Blue2,
        color1 = Blue1,
        colorDetail = BlueDetail,
        text = "Join",
        onClick = {}
    )
}