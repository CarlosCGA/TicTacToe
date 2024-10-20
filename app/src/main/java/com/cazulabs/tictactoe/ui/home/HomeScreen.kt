package com.cazulabs.tictactoe.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cazulabs.tictactoe.R
import com.cazulabs.tictactoe.ui.theme.Blue1
import com.cazulabs.tictactoe.ui.theme.Blue2
import com.cazulabs.tictactoe.ui.theme.Blue3
import com.cazulabs.tictactoe.ui.theme.BlueDetail
import com.cazulabs.tictactoe.ui.theme.Orange1
import com.cazulabs.tictactoe.ui.theme.Orange2
import com.cazulabs.tictactoe.ui.theme.Orange3
import com.cazulabs.tictactoe.ui.theme.OrangeDetail

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToGame: (String, String, Boolean) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Background()
        Body(viewModel, navigateToGame)
    }
}

@Composable
fun Body(viewModel: HomeViewModel, navigateToGame: (String, String, Boolean) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.75F))
        Image(
            modifier = Modifier.size(300.dp),
            painter = painterResource(id = R.drawable.ic_tictactoe_clashroyale_remix),
            contentDescription = "app icon"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ClashRoyaleButton(
                color3 = Orange3,
                color2 = Orange2,
                color1 = Orange1,
                colorSpotShadow = Color.Red,
                colorDetail = OrangeDetail,
                text = "Battle",
                onClick = { viewModel.onCreateGame(navigateToGame) }
            )

            Spacer(modifier = Modifier.size(16.dp))

            JoinTheGame(onJoinGame = { viewModel.onJoinGame(it, navigateToGame) })
        }

        Spacer(modifier = Modifier.weight(1F))
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClashRoyaleButton(
    color3: Color,
    color2: Color,
    color1: Color,
    colorSpotShadow: Color = Color.Black,
    colorDetail: Color,
    text: String,
    onClick: () -> Unit
) {
    //Black border
    Box(
        modifier = Modifier
            .height(65.dp)
            .width(130.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        //Blue3
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color3, RoundedCornerShape(6.dp))
        ) {
            //Blue2
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp)
                    .background(color2, RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                //Blue1
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color1, RoundedCornerShape(6.dp))
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(6.dp),
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
                                    val width = size.width
                                    drawOval(
                                        color = colorDetail,
                                        topLeft = Offset(width + 2F, 5F),
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

@Composable
fun Background() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.home_background),
        contentDescription = "background",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun JoinTheGame(onJoinGame: (String) -> Unit) {
    var gameName by rememberSaveable {
        mutableStateOf("")
    }

    ClashRoyaleButton(
        color3 = Blue3,
        color2 = Blue2,
        color1 = Blue1,
        colorDetail = BlueDetail,
        text = "Join",
        onClick = {
            onJoinGame(gameName)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BattleButton() {
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
fun JoinButton() {
    ClashRoyaleButton(
        color3 = Blue3,
        color2 = Blue2,
        color1 = Blue1,
        colorDetail = BlueDetail,
        text = "Join",
        onClick = {}
    )
}

@ExperimentalComposeUiApi
@Composable
fun OutlinedText(
    text: String,
    modifier: Modifier = Modifier,
    fillColor: Color = Color.Unspecified,
    outlineColor: Color,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    outlineDrawStyle: Stroke = Stroke(),
) {
    Box(modifier = modifier) {
        Text(
            text = text,
            modifier = Modifier.semantics { invisibleToUser() },
            color = outlineColor,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = null,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            style = style.copy(
                shadow = null,
                drawStyle = outlineDrawStyle,
            ),
        )

        Text(
            text = text,
            color = fillColor,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            style = style,
        )
    }
}