package com.ipekkochisarli.forinvest_crypto.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ipekkochisarli.forinvest_crypto.navigation.AppNavHost
import com.ipekkochisarli.forinvest_crypto.ui.theme.DarkOutline
import dagger.hilt.android.AndroidEntryPoint
import com.ipekkochisarli.forinvest_crypto.ui.theme.ForInvestCryptoTheme
import com.ipekkochisarli.forinvest_crypto.ui.theme.PrimaryBlue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ForInvestCryptoTheme {
                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val showBottomBar = when (currentDestination?.route) {
                    "profile", "onBoarding", "coinDetail/{coinId}" -> false
                    else -> true
                }

                Scaffold(bottomBar = {
                    if (showBottomBar) {
                        BottomAppBar {
                            BottomNavigationItem(
                                selected = currentDestination?.route == "home",
                                onClick = { navController.navigate("home") },
                                icon = {
                                    Icon(
                                        Icons.Default.Home,
                                        contentDescription = "Home",
                                        tint = if (currentDestination?.route == "home") PrimaryBlue else DarkOutline
                                    )
                                },
                                label = {
                                    Text(
                                        "Home",
                                        color = if (currentDestination?.route == "home") PrimaryBlue else DarkOutline
                                    )
                                },
                                alwaysShowLabel = true,
                                selectedContentColor = PrimaryBlue,
                                unselectedContentColor = DarkOutline,
                            )

                        }
                    }
                }, topBar = {
                    if (showBottomBar) {
                        TopAppBar(title = { Text("ForInvestCrypto") })
                    }
                }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppNavHost(navController)
                    }
                }
            }
        }
    }
}