package com.cazulabs.tictactoe.ui.model

import com.cazulabs.tictactoe.data.network.model.GameData
import com.cazulabs.tictactoe.data.network.model.PlayerData

data class GameModel(
    val board: List<PlayerType>,
    val gameId: String,
    val player1: PlayerModel,
    val player2: PlayerModel?,
    val playerTurn: PlayerModel,
    val isGameReady: Boolean = false,
    val isMyTurn: Boolean = false
) {
    fun toData(): GameData {
        return GameData(
            board = board.map { it.id },
            gameId = gameId,
            player1 = player1.toData(),
            player2 = player2?.toData(),
            playerTurn = playerTurn.toData()
        )
    }
}

data class PlayerModel(
    val playerId: String,
    val playerType: PlayerType
) {
    fun toData(): PlayerData {
        return PlayerData(
            playerId = playerId,
            playerType = playerType.id
        )
    }
}

sealed class PlayerType(val id: Int, val symbol: String) {
    data object FirstPlayer: PlayerType(2, "X")
    data object SecondPlayer: PlayerType(3, "0")
    data object Empty: PlayerType(0, "")

    companion object {
        fun getPlayerById(id: Int?): PlayerType {
            return when(id) {
                FirstPlayer.id -> { FirstPlayer }

                SecondPlayer.id -> { SecondPlayer }

                else -> { Empty }
            }
        }
    }
}