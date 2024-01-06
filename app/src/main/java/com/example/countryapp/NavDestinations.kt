package com.example.countryapp

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface CountryDestination {
    val route: String
}

object Search : CountryDestination {
    override val route = "SearchScreen"
}

object Details : CountryDestination {
    override val route = "details"
    private const val countryNameArg = "country_name"
    val routeWithArgs = "${route}/{${countryNameArg}}"
    val arguments = listOf(
        navArgument(countryNameArg) { type = NavType.StringType }
    )
}
