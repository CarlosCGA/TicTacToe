package com.cazulabs.tictactoe.ui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cazulabs.tictactoe.ui.theme.DialogBackgroundDarkGradient
import com.cazulabs.tictactoe.ui.theme.DialogBackgroundDarkGradientDetail
import com.cazulabs.tictactoe.ui.theme.DialogBackgroundLightGradient
import com.cazulabs.tictactoe.ui.theme.DialogBackgroundLightGradientDetail
import com.cazulabs.tictactoe.ui.theme.DialogBorder
import com.cazulabs.tictactoe.ui.theme.DialogBorderDarkGradient
import com.cazulabs.tictactoe.ui.theme.DialogBorderLight2
import com.cazulabs.tictactoe.ui.theme.DialogBorderLightGradient
import com.cazulabs.tictactoe.ui.theme.DialogShadow

/*
@Preview(showSystemUi = true)
@Composable
fun ClashRoyaleDialog() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Box(modifier = Modifier.clip(RoundedCornerShape(6.dp))) {
            Column {
                Spacer(modifier = Modifier.size(16.dp))
                ClashRoyaleButton(
                    color3 = Blue3,
                    color2 = Blue2,
                    color1 = Blue1,
                    colorDetail = BlueDetail,
                    text = "OK",
                    onClick = {}
                )
            }
        }
    }
}
*/

@Preview(showBackground = true)
@Composable
fun Alfombra() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ClashRoyaleDialog()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClashRoyaleDialog() {
    val cornerRadius = 12.dp
    Dialog(
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .height(300.dp)
                .background(DialogShadow, RoundedCornerShape(cornerRadius))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp)
                    .background(DialogBorderLightGradient, RoundedCornerShape(cornerRadius))
                    .innerShadow(
                        color = DialogBorderDarkGradient,
                        cornersRadius = cornerRadius,
                        spread = 1.dp,
                        blur = 6.dp,
                        offsetY = -(2).dp
                    )
            ) {
                val borderGradientBrush = Brush.verticalGradient(
                    colors = listOf(
                        DialogBorder,
                        DialogBorderLight2
                    )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 4.dp, start = 2.dp, end = 2.dp, top = 2.dp)
                        .border(0.5.dp, borderGradientBrush, RoundedCornerShape(cornerRadius))
                ) {
                    val backgroundGradientBrush = Brush.verticalGradient(
                        colors = listOf(
                            DialogBackgroundLightGradient,
                            DialogBackgroundDarkGradient
                        )
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundGradientBrush, RoundedCornerShape(cornerRadius))
                    ) {
                        val backgroundGradientDetailBrush = Brush.verticalGradient(
                            colors = listOf(
                                DialogBackgroundLightGradientDetail,
                                DialogBackgroundDarkGradientDetail
                            )
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .padding(top = 3.dp, start = 3.dp, end = 3.dp)
                                .background(
                                    backgroundGradientDetailBrush,
                                    RoundedCornerShape(
                                        topStart = cornerRadius - 2.dp,
                                        topEnd = cornerRadius - 2.dp,
                                        bottomStart = 4.dp,
                                        bottomEnd = 4.dp
                                    )
                                )
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 8.dp)
                        ) {
                            OutlinedText(text = "Reward limit reached!", fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}

