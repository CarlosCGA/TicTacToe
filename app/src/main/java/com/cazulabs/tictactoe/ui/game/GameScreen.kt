package com.cazulabs.tictactoe.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cazulabs.tictactoe.ui.model.GameModel

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    gameId: String,
    playerId: String,
    owner: Boolean
) {

    LaunchedEffect(key1 = true) {
        viewModel.joinToGame(gameId, playerId, owner)
    }

    val game: GameModel? by viewModel.game.collectAsState()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "IdMatch: $gameId")

        Spacer(modifier = Modifier.size(16.dp))

        Board(game)
    }

}

@Composable
fun Board(game: GameModel?) {

    val status = if(game?.isGameReady == true)
        "Your turn / Opponent turn"
    else
        "Waiting for opponent..."

    Text(text = status)

    Spacer(modifier = Modifier.size(8.dp))

    Column {
        Row {
            GameItem()
            GameItem()
            GameItem()
        }

        Row {
            GameItem()
            GameItem()
            GameItem()
        }

        Row {
            GameItem()
            GameItem()
            GameItem()
        }
    }
}


@Composable
fun GameItem() {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(64.dp)
            .border(BorderStroke(2.dp, Color.Black)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "X")
    }
}