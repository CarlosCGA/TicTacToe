package com.cazulabs.tictactoe.ui.model

import com.cazulabs.tictactoe.data.network.model.PlayerData

data class GameModel(
    val board: List<PlayerType>,
    val gameId: String,
    val player1: PlayerModel,
    val player2: PlayerModel?,
    val playerTurn: PlayerModel
)

data class PlayerModel(
    val playerId: String,
    val playerType: PlayerType
)

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