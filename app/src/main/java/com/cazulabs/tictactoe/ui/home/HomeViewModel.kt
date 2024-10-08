package com.cazulabs.tictactoe.ui.home

import androidx.lifecycle.ViewModel
import com.cazulabs.tictactoe.data.network.FirebaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val firebaseService: FirebaseService) :
    ViewModel() {
    fun onCreateGame() {
        TODO("Not yet implemented")
    }

    fun onJoinGame(game: String) {
        TODO("Not yet implemented")
    }


}