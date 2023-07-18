package com.ccastro.maas.presentation.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ccastro.maas.presentation.screens.login.LoginScreen
import com.ccastro.maas.presentation.screens.singup.SingupScreen


fun NavGraphBuilder.authNavGrap(navHostController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthNavScreens.Login.route
    ){
        composable(route = AuthNavScreens.Login.route){
            LoginScreen(navHostController = navHostController)
        }
        composable(route = AuthNavScreens.Singup.route){
            SingupScreen(navHostController = navHostController)
        }
    }
}

sealed class AuthNavScreens(val route: String){
    object Login: AuthNavScreens(route = "login")
    object Singup: AuthNavScreens(route = "singup")
}