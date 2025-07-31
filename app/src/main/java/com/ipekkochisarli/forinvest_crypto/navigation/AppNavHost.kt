package com.ipekkochisarli.forinvest_crypto.navigation

import CoinDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ipekkochisarli.forinvest_crypto.core.ui.ScreenNames
import com.ipekkochisarli.forinvest_crypto.features.home.presentation.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = ScreenNames.Home.route) {

        composable(ScreenNames.Home.route) {
            HomeScreen(onCoinClick = { coinId ->
                navController.navigate(ScreenNames.CoinDetail.createRoute(coinId))
            })
        }

        composable(
            route = ScreenNames.CoinDetail.route,
            arguments = listOf(navArgument("coinId") { type = NavType.StringType })
        ) { backStackEntry ->
            val coinId = backStackEntry.arguments?.getString("coinId") ?: ""
            CoinDetailScreen(
                coinId = coinId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}