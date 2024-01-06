package com.example.countryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.countryapp.ui.DetailScreen
import com.example.countryapp.ui.SearchBarScreen
import com.example.countryapp.ui.theme.CountryAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCountryApp()
        }
    }
}

@Composable
fun MyCountryApp() {
    CountryAppTheme() {
        val navController = rememberNavController()
        Scaffold {
            NavHost(
                navController = navController,
                startDestination = Search.route,
                modifier = Modifier.padding(it)
            ) {
                composable(route = Search.route) {
                    SearchBarScreen() { name ->
                        navController.navigate("${Details.route}/$name")
                    }
                }
                composable(
                    route = Details.routeWithArgs,
                    arguments = Details.arguments
                ) {
                    DetailScreen()
                }
            }
        }
    }
}