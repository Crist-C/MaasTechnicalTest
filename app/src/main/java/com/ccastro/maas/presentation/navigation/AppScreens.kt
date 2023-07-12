package com.ccastro.maas.presentation.navigation

sealed class AppScreens(val route: String) {
    object Home: AppScreens("home")
    object AddUserCard: AppScreens("AddUserCard")
    object Login: AppScreens("Login")
    object Singup: AppScreens("Singup")
    object Profile: AppScreens("Profile")
    object  Map: AppScreens("Map")
}
