package com.cazulabs.tictactoe.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1F))

        CreateGame(onCreateGame = { viewModel.onCreateGame() })

        Spacer(modifier = Modifier.weight(1F))

        Divider(
            Modifier
                .fillMaxWidth()
                .height(2.dp)
        )

        Spacer(modifier = Modifier.weight(1F))

        JoinTheGame(onJoinGame = { viewModel.onJoinGame(it) })

        Spacer(modifier = Modifier.weight(1F))
    }
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