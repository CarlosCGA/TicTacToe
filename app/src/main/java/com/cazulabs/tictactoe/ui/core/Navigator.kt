package com.cazulabs.tictactoe.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cazulabs.tictactoe.ui.core.Routes.GAME
import com.cazulabs.tictactoe.ui.core.Routes.HOME
import com.cazulabs.tictactoe.ui.game.GameScreen
import com.cazulabs.tictactoe.ui.home.HomeScreen

@Composable
fun ContentWrapper(navigationController: NavHostController) {
    NavHost(navController = navigationController, startDestination = HOME.route) {
        composable(HOME.route) {
            HomeScreen(navigateToGame = {
                navigationController.navigate(HOME.route)
            })
        }

        composable(GAME.route) {
            GameScreen()
        }
    }
}

sealed class Routes(val route: String) {
    data object HOME: Routes("home")
    data object GAME: Routes("game")
}