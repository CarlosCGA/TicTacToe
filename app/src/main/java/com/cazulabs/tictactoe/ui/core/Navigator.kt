package com.cazulabs.tictactoe.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cazulabs.tictactoe.ui.core.Routes.GAME
import com.cazulabs.tictactoe.ui.core.Routes.HOME
import com.cazulabs.tictactoe.ui.game.GameScreen
import com.cazulabs.tictactoe.ui.home.HomeScreen

@Composable
fun ContentWrapper(navigationController: NavHostController) {
    NavHost(navController = navigationController, startDestination = HOME.route) {
        composable(HOME.route) {
            HomeScreen(navigateToGame = { gameId, playerId, owner ->
                navigationController.navigate(GAME.createRoute(gameId, playerId, owner))
            })
        }

        composable(
            GAME.route,
            arguments = listOf(
                navArgument("gameId") { type = NavType.StringType },
                navArgument("playerId") { type = NavType.StringType },
                navArgument("owner") { type = NavType.BoolType },
            )
        ) {
            GameScreen(
                gameId = it.arguments?.getString("gameId")!!,
                playerId = it.arguments?.getString("playerId")!!,
                owner = it.arguments?.getBoolean("owner") ?: false,
            )
        }
    }
}

sealed class Routes(val route: String) {
    data object HOME : Routes("home")
    data object GAME : Routes("game/{gameId}/{playerId}/{owner}") {
        fun createRoute(gameId: String, playerId: String, owner: Boolean): String {
            return "game/$gameId/$playerId/$owner"
        }
    }
}