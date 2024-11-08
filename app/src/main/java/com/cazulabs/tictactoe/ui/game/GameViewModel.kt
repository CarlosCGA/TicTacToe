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

    private val _game = MutableStateFlow<GameModel?>(null)
    val game: StateFlow<GameModel?> = _game

    private val _winner = MutableStateFlow<PlayerType?>(null)
    val winner: StateFlow<PlayerType?> = _winner


    fun joinToGame(gameId: String, playerId: String, owner: Boolean) {
        this.playerId = playerId

        if (owner)
            join(gameId)
        else
            joinGameLikeGuest(gameId)
    }

    private fun join(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinToGame(gameId).collect {
                val result =
                    it?.copy(isGameReady = it.player2 != null, isMyTurn = isMyTurn(it.playerTurn))
                _game.value = result
                verifyWinner()
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

            join(gameId)
        }
    }

    private fun isMyTurn(playerTurn: PlayerModel) = playerTurn.playerId == playerId

    fun onPlayerMoves(position: Int) {
        val currentGame = _game.value ?: return

        if (currentGame.isGameReady && currentGame.board[position] == PlayerType.Empty && isMyTurn(
                currentGame.playerTurn
            )
        ) {
            viewModelScope.launch {
                val newBoard = currentGame.board.toMutableList()
                newBoard[position] = getPlayer() ?: PlayerType.Empty
                firebaseService.updateGame(
                    currentGame.copy(
                        board = newBoard,
                        playerTurn = getOpponentPlayer()!!
                    ).toData()
                )
            }
        }
    }

    fun getPlayer(): PlayerType? {
        return when {
            (game.value?.player1?.playerId == playerId) -> PlayerType.FirstPlayer
            (game.value?.player2?.playerId == playerId) -> PlayerType.SecondPlayer
            else -> null
        }
    }

    private fun getOpponentPlayer(): PlayerModel? {
        return when {
            (game.value?.player1?.playerId == playerId) -> game.value?.player2
            (game.value?.player2?.playerId == playerId) -> game.value?.player1
            else -> null
        }
    }

    private fun verifyWinner() {
        val board = _game.value?.board
        if (!board.isNullOrEmpty() && board.size == 9) {
            when {
                isGameWon(board, PlayerType.FirstPlayer) -> {
                    _winner.value = PlayerType.FirstPlayer
                }

                isGameWon(board, PlayerType.SecondPlayer) -> {
                    _winner.value = PlayerType.SecondPlayer
                }

                isADraft(board) -> {
                    _winner.value = PlayerType.Empty
                }
            }
        }
    }

    private fun isGameWon(board: List<PlayerType>, playerType: PlayerType): Boolean {

        return when {
            //ROWS
            //ROW 1
            (board[0] == playerType && board[1] == playerType && board[2] == playerType) -> true
            //ROW 2
            (board[3] == playerType && board[4] == playerType && board[5] == playerType) -> true
            //ROW 3
            (board[6] == playerType && board[7] == playerType && board[8] == playerType) -> true

            //COLUMNS
            //COLUMN 1
            (board[0] == playerType && board[3] == playerType && board[6] == playerType) -> true
            //COLUMN 2
            (board[1] == playerType && board[4] == playerType && board[7] == playerType) -> true
            //COLUMN 3
            (board[2] == playerType && board[5] == playerType && board[8] == playerType) -> true

            //DIAGONALS
            //DIAGONAL 1
            (board[0] == playerType && board[4] == playerType && board[8] == playerType) -> true
            //DIAGONAL 2
            (board[2] == playerType && board[4] == playerType && board[6] == playerType) -> true

            else -> false
        }
    }

    private fun isADraft(board: List<PlayerType>) =
        (board[0] == PlayerType.FirstPlayer || board[0] == PlayerType.SecondPlayer) &&
                (board[1] == PlayerType.FirstPlayer || board[1] == PlayerType.SecondPlayer) &&
                (board[2] == PlayerType.FirstPlayer || board[2] == PlayerType.SecondPlayer) &&
                (board[3] == PlayerType.FirstPlayer || board[3] == PlayerType.SecondPlayer) &&
                (board[4] == PlayerType.FirstPlayer || board[4] == PlayerType.SecondPlayer) &&
                (board[5] == PlayerType.FirstPlayer || board[5] == PlayerType.SecondPlayer) &&
                (board[6] == PlayerType.FirstPlayer || board[6] == PlayerType.SecondPlayer) &&
                (board[7] == PlayerType.FirstPlayer || board[7] == PlayerType.SecondPlayer) &&
                (board[8] == PlayerType.FirstPlayer || board[8] == PlayerType.SecondPlayer)

}