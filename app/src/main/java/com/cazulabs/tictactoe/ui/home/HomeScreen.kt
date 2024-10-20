package com.cazulabs.tictactoe.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cazulabs.tictactoe.R
import com.cazulabs.tictactoe.ui.core.ClashRoyaleButton
import com.cazulabs.tictactoe.ui.core.ClashRoyaleDialog
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
    var showJoinGameDialog by rememberSaveable {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Background()
        Body(
            viewModel,
            navigateToGame,
            onClickJoinGameButton = { showJoinGameDialog = true }
        )
    }

    if (showJoinGameDialog)
        JoinGameDialog(
            onDismiss = { showJoinGameDialog = false },
            onJoinGame = { gameName -> viewModel.onJoinGame(gameName, navigateToGame) }
        )
}

@Composable
fun Body(
    viewModel: HomeViewModel,
    navigateToGame: (String, String, Boolean) -> Unit,
    onClickJoinGameButton: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
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
            CreateGameButton(onClick = { viewModel.onCreateGame(navigateToGame) })

            Spacer(modifier = Modifier.size(16.dp))

            JoinGameButton(onClick = onClickJoinGameButton)
        }

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
fun CreateGameButton(onClick: () -> Unit) {
    ClashRoyaleButton(
        color3 = Orange3,
        color2 = Orange2,
        color1 = Orange1,
        colorSpotShadow = Color.Red,
        colorDetail = OrangeDetail,
        text = "Battle",
        onClick = onClick
    )
}

@Composable
fun JoinGameButton(onClick: () -> Unit) {
    ClashRoyaleButton(
        color3 = Blue3,
        color2 = Blue2,
        color1 = Blue1,
        colorDetail = BlueDetail,
        text = "Join",
        onClick = onClick
    )
}

@Composable
fun JoinGameDialog(onDismiss: () -> Unit, onJoinGame: (String) -> Unit) {
    ClashRoyaleDialog(
        title = "Game code",
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var gameName by rememberSaveable {
                    mutableStateOf("")
                }

                Spacer(modifier = Modifier.size(16.dp))

                OutlinedTextField(
                    value = gameName,
                    onValueChange = { gameName = it },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(R.font.supercell_magic_regular))
                    )
                )

                Spacer(modifier = Modifier.size(16.dp))

                ClashRoyaleButton(
                    color3 = Blue3,
                    color2 = Blue2,
                    color1 = Blue1,
                    colorDetail = BlueDetail,
                    text = "OK",
                    onClick = {
                        onJoinGame(gameName)
                        onDismiss()
                    }
                )

                Spacer(modifier = Modifier.size(16.dp))
            }
        },
        onDismiss = onDismiss
    )
}