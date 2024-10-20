package com.cazulabs.tictactoe.ui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cazulabs.tictactoe.ui.theme.Blue1
import com.cazulabs.tictactoe.ui.theme.BlueDetail
import com.cazulabs.tictactoe.ui.theme.DialogBackgroundContent
import com.cazulabs.tictactoe.ui.theme.DialogBackgroundDarkGradient
import com.cazulabs.tictactoe.ui.theme.DialogBackgroundDarkGradientDetail
import com.cazulabs.tictactoe.ui.theme.DialogBackgroundLightGradient
import com.cazulabs.tictactoe.ui.theme.DialogBackgroundLightGradientDetail
import com.cazulabs.tictactoe.ui.theme.DialogBorder
import com.cazulabs.tictactoe.ui.theme.DialogBorderDarkGradient
import com.cazulabs.tictactoe.ui.theme.DialogBorderLight2
import com.cazulabs.tictactoe.ui.theme.DialogBorderLightGradient
import com.cazulabs.tictactoe.ui.theme.DialogShadow
import com.cazulabs.tictactoe.ui.theme.RedCloseDialogButtonBackground
import com.cazulabs.tictactoe.ui.theme.RedCloseDialogButtonBorderDark

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClashRoyaleDialog(
    title: String,
    content: @Composable () -> Unit,
    onDismiss: () -> Unit
) {
    val cornerRadius = 12.dp
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .wrapContentHeight()
                .background(DialogShadow, RoundedCornerShape(cornerRadius))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                        .fillMaxWidth()
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
                            .fillMaxWidth()
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

                        Column(modifier = Modifier.fillMaxWidth()) {
                            val defaultSize = 40.dp
                            val verticalRowPadding = 6.dp

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(defaultSize + verticalRowPadding)
                                    .padding(horizontal = 10.dp, vertical = verticalRowPadding),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    OutlinedText(
                                        text = title,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        fontSize = 16.sp
                                    )
                                }

                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    ClashRoyaleSquareButton(
                                        color3 = RedCloseDialogButtonBorderDark,
                                        color2 = RedCloseDialogButtonBackground,
                                        color1 = Blue1,
                                        colorDetail = BlueDetail,
                                        text = "X",
                                        onClick = { onDismiss() }
                                    )
                                }
                            }

                            //Content
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                                    .background(DialogBackgroundContent, RoundedCornerShape(4.dp)),
                            ) {
                                content()
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
fun DialogPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ClashRoyaleDialog(
            title = "Reward limit reached!",
            content = {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Hello world!"
                )
            },
            onDismiss = {}
        )
    }
}