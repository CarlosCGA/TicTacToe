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
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import com.cazulabs.tictactoe.ui.model.PlayerType

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
    val winner: PlayerType? by viewModel.winner.collectAsState()

    if (winner != null) {
        WonScreen(viewModel.getPlayer()!!, winner!!)
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "IdMatch: $gameId")

            Spacer(modifier = Modifier.size(16.dp))

            Board(
                game = game,
                onItemClicked = { position ->
                    viewModel.onPlayerMoves(position)
                }
            )
        }
    }
}

@Composable
fun Board(game: GameModel?, onItemClicked: (Int) -> Unit) {
    if (game == null) return

    val status = if (game.isGameReady)
        if (game.isMyTurn)
            "Your turn"
        else
            "Opponent turn"
    else
        "Waiting for opponent..."

    Text(text = status)

    Spacer(modifier = Modifier.size(8.dp))

    Column {
        Row {
            GameItem(game.board[0]) { onItemClicked(0) }
            GameItem(game.board[1]) { onItemClicked(1) }
            GameItem(game.board[2]) { onItemClicked(2) }
        }

        Row {
            GameItem(game.board[3]) { onItemClicked(3) }
            GameItem(game.board[4]) { onItemClicked(4) }
            GameItem(game.board[5]) { onItemClicked(5) }
        }

        Row {
            GameItem(game.board[6]) { onItemClicked(6) }
            GameItem(game.board[7]) { onItemClicked(7) }
            GameItem(game.board[8]) { onItemClicked(8) }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameItem(playerType: PlayerType, onItemClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .size(64.dp)
            .border(BorderStroke(2.dp, Color.Black)),
        onClick = { onItemClicked() }) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = playerType.symbol)
        }
    }
}

@Composable
fun WonScreen(player: PlayerType, winner: PlayerType) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = when (winner) {
                player -> "YOU WIN!"
                PlayerType.Empty -> "REMATCH!"
                else -> "Damn. You are a piece of shit..."
            }
        )
    }
}