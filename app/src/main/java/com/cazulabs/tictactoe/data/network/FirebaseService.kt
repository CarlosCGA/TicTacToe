package com.cazulabs.tictactoe.data.network

import com.cazulabs.tictactoe.data.network.model.GameData
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "games"
    }

    fun createGame(gameData: GameData): String {
        val gameReference = reference.child(PATH).push()
        val key = gameReference.key
        val newGame = gameData.copy(gameId = key)
        gameReference.setValue(newGame)

        return newGame.gameId.orEmpty()
    }

}