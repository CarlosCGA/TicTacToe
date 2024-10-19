package com.cazulabs.tictactoe.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1F))

        CreateGame(onCreateGame = {
            viewModel.onCreateGame(navigateToGame)
        })

        Spacer(modifier = Modifier.weight(1F))

        Divider(
            Modifier
                .fillMaxWidth()
                .height(2.dp)
        )

        Spacer(modifier = Modifier.weight(1F))

        JoinTheGame(onJoinGame = { viewModel.onJoinGame(it, navigateToGame) })

        Spacer(modifier = Modifier.weight(1F))
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
fun CreateGame(onCreateGame: () -> Unit) {
    Button(onClick = { onCreateGame() }) {
        Text(text = "Create game")
    }
}

@Composable
fun JoinTheGame(onJoinGame: (String) -> Unit) {
    var gameName by rememberSaveable {
        mutableStateOf("")
    }
    TextField(value = gameName, onValueChange = { gameName = it })

    Button(onClick = { onJoinGame(gameName) }, enabled = gameName.isNotEmpty()) {
        Text(text = "Join the game")
    }
}

@Preview(showBackground = true)
@Composable
fun BattleButton() {
    ClashRoyaleButton(
        color3 = Orange3,
        color2 = Orange2,
        color1 = Orange1,
        colorDetail = OrangeDetail,
        text = "Battle"
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
        text = "Join"
    )
}

@Composable
fun ClashRoyaleButton(
    color3: Color,
    color2: Color,
    color1: Color,
    colorDetail: Color,
    text: String
) {
    Box(modifier = Modifier.padding(8.dp)) {
        //Black border
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(100.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(6.dp)),
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
                                spotColor = Color.Black
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
                                    .offset(22.5.dp, 27.dp)
                                    .rotate(-45F),
                                contentAlignment = Alignment.TopEnd
                            ) {

                                Box {
                                    Canvas(modifier = Modifier.fillMaxSize()) {
                                        val width = size.width
                                        drawOval(
                                            color = colorDetail,
                                            topLeft = Offset(width - 7F, 1F),
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
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 4.dp),
                                    text = text,
                                    textAlign = TextAlign.Center,
                                    fontSize = 18.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Clip
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}