package com.ccastro.maas.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ccastro.maas.presentation.MainActivity
import com.ccastro.maas.presentation.screens.addusercard.AddUserCardScreen
import com.ccastro.maas.presentation.screens.home.HomeScreen
import com.ccastro.maas.presentation.screens.map.MapScreen
import com.ccastro.maas.presentation.screens.profile.ProfileScreen


fun NavGraphBuilder.homeNavGrap(navHostController: NavHostController, activity: MainActivity) {
    navigation(
        route = Graph.HOME,
        startDestination = HomeNavigationScreens.Home.route
    ){

        composable(route = HomeNavigationScreens.Home.route){
            HomeScreen(navHostController = navHostController, activity)
        }
        composable(route = HomeNavigationScreens.AddUserCard.route){
            AddUserCardScreen(navHostController = navHostController)
        }
        composable(route = HomeNavigationScreens.Profile.route){
            ProfileScreen(navHostController = navHostController)
        }
        composable(
            route = HomeNavigationScreens.Map.route,
            arguments = listOf(navArgument("nearPlaces"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("nearPlaces")?.let {nearPlaces ->
                MapScreen(navHostController = navHostController, nearPlaces = nearPlaces)
            }
        }

    }
}

sealed class HomeNavigationScreens(val route: String){
    object Home: HomeNavigationScreens("home")
    object AddUserCard: HomeNavigationScreens("add_user_card")
    object Profile: HomeNavigationScreens("profile")
    object Map: HomeNavigationScreens("map/{nearPlaces}"){
        fun passNearStopPlaces(nearPlaces: String) = "map/$nearPlaces"
    }
}