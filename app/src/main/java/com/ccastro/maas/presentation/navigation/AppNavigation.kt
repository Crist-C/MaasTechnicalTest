package com.ccastro.maas.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ccastro.maas.presentation.screens.AddUserCard.AddUserCardScreen
import com.ccastro.maas.presentation.screens.login.LoginScreen
import com.ccastro.maas.presentation.screens.Home.HomeScreen
import com.ccastro.maas.presentation.screens.Home.components.demoCardList
import com.ccastro.maas.presentation.screens.Home.components.demoStoppinPlaceList
import com.ccastro.maas.presentation.screens.Profile.ProfileScreen
import com.ccastro.maas.presentation.screens.singup.SingupScreen

@Composable
fun AppNavigation(navHostController: NavHostController) {
    
    NavHost(
        navController = navHostController,
        startDestination = AppScreens.Login.route
    ){
        composable(route = AppScreens.Login.route){
            LoginScreen(navHostController = navHostController)
        }

        composable(route = AppScreens.Singup.route){
            SingupScreen(navHostController = navHostController)
        }

        composable(route = AppScreens.Profile.route){
            ProfileScreen(navHostController = navHostController)
        }

        composable(route = AppScreens.Home.route){
            HomeScreen(navHostController = navHostController, userCards = demoCardList(), stoppingPlace = demoStoppinPlaceList())
        }

        composable(route = AppScreens.AddUserCard.route){
            AddUserCardScreen(navHostController = navHostController)
        }

    }
}