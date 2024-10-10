package com.cazulabs.tictactoe.data.network

import com.cazulabs.tictactoe.data.network.model.GameData
import com.cazulabs.tictactoe.ui.model.GameModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    fun getGameData(gameId: String): GameData {
        val reference = reference.child(PATH)
        val gameData = reference.get().result.child(gameId).getValue(GameData::class.java)
        return gameData ?: GameData()
    }

    fun joinToGame(gameId: String): Flow<GameModel?> {
        return reference.child("$PATH/$gameId").snapshots.map { dataSnapshot ->
            dataSnapshot.getValue(GameData::class.java)?.toModel()
        }

        /*
        val reference = reference.child(PATH)
        val gameData = reference.get().result.child(gameId).getValue(GameData::class.java)
        */
    }

}