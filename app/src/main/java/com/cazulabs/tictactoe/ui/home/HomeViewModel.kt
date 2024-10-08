package com.cazulabs.tictactoe.ui.home

import androidx.lifecycle.ViewModel
import com.cazulabs.tictactoe.data.network.FirebaseService
import com.cazulabs.tictactoe.data.network.model.GameData
import com.cazulabs.tictactoe.data.network.model.PlayerData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {
    fun onCreateGame(navigateToGame: (String, String, Boolean) -> Unit) {
        val game = createNewGame()
        val gameId = firebaseService.createGame(game)
        val playerId = game.player1?.userId.orEmpty()
        val owner = true

        navigateToGame(gameId, playerId, owner)
    }

    private fun createNewGame(): GameData {
        val currentPlayer = PlayerData(playerType = 1)
        return GameData(
            board = List(9) { 0 },
            player1 = currentPlayer,
            player2 = null,
            playerTurn = currentPlayer
        )
    }

    fun onJoinGame(gameId: String, navigateToGame: (String, String, Boolean) -> Unit) {
        val playerId = createUserId()
        val owner = false
        navigateToGame(gameId, playerId, owner)
    }

    private fun createUserId(): String = Calendar.getInstance().timeInMillis.hashCode().toString()


}