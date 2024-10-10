package com.cazulabs.tictactoe.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.tictactoe.data.network.FirebaseService
import com.cazulabs.tictactoe.data.network.model.GameData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {

    private lateinit var playerId: String

    val _game = MutableLiveData<GameData>()
    val game: LiveData<GameData> = _game

    private fun getMatchState(gameId: String) {
        _game.value = firebaseService.getGameData(gameId)
    }

    fun joinToGame(gameId: String, playerId: String, owner: Boolean) {
        this.playerId = playerId

        if(owner)
            joinGameLikeOwner(gameId)
        else
            joinGameLikeGuest(gameId)
    }

    private fun joinGameLikeOwner(gameId: String) {
        viewModelScope.launch {
            firebaseService.joinToGame(gameId).collect {
                Log.i("CARLOS", it.toString())
            }
        }
    }

    private fun joinGameLikeGuest(gameId: String) {

    }

}