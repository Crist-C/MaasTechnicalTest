package com.ccastro.maas.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ccastro.maas.presentation.screens.addUserCard.AddUserCardScreen
import com.ccastro.maas.presentation.screens.login.LoginScreen
import com.ccastro.maas.presentation.screens.mainMenu.MainMenuScreen
import com.ccastro.maas.presentation.screens.mainMenu.components.demoCardList
import com.ccastro.maas.presentation.screens.mainMenu.components.demoStoppinPlaceList
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

        composable(route = AppScreens.Home.route){
            MainMenuScreen(navHostController = navHostController, userCards = demoCardList(), stoppingPlace = demoStoppinPlaceList())
        }

        composable(route = AppScreens.AddUserCard.route){
            AddUserCardScreen(navHostController = navHostController)
        }

    }
}