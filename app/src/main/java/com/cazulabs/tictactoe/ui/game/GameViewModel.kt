package com.cazulabs.tictactoe.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.tictactoe.data.network.FirebaseService
import com.cazulabs.tictactoe.ui.model.GameModel
import com.cazulabs.tictactoe.ui.model.PlayerModel
import com.cazulabs.tictactoe.ui.model.PlayerType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {

    private lateinit var playerId: String

    val _game = MutableStateFlow<GameModel?>(null)
    val game: StateFlow<GameModel?> = _game

    fun joinToGame(gameId: String, playerId: String, owner: Boolean) {
        this.playerId = playerId

        if (owner)
            joinGameLikeOwner(gameId)
        else
            joinGameLikeGuest(gameId)
    }

    private fun joinGameLikeOwner(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinToGame(gameId).collect {
                val result = it?.copy(isGameReady = it.player2 != null)
                _game.value = result
            }
        }
    }

    private fun joinGameLikeGuest(gameId: String) {
        viewModelScope.launch {

            firebaseService.joinToGame(gameId).take(1).collect {
                var result = it

                if (result != null) {
                    result = result.copy(player2 = PlayerModel(playerId, PlayerType.SecondPlayer))
                    firebaseService.updateGame(result.toData())
                }
            }

            firebaseService.joinToGame(gameId).collect {
                val result = it?.copy(isGameReady = it.player2 != null)
                _game.value = result
            }
        }
    }

}