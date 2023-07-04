package com.ccastro.maas.presentation.navigation

sealed class AppScreens(val route: String) {
    object Home: AppScreens("login")
    object AddUserCard: AppScreens("AddUserCard")
    object Login: AppScreens("Login")
    object Singup: AppScreens("Singup")
    object Profile: AppScreens("Profile")
}
