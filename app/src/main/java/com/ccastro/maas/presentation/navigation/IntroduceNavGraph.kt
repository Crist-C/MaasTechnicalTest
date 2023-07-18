package com.ccastro.maas.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ccastro.maas.presentation.screens.appPresentation.components.AppPresentationScreen

fun NavGraphBuilder.introduceNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.INTRODUCE,
        startDestination = IntroduceScreens.Presentation.route
    ){

        composable(route = IntroduceScreens.Presentation.route){
            AppPresentationScreen(navHostController = navHostController)
        }

        composable(route = IntroduceScreens.Splash.route){
            //AppPresentationScreen(navHostController = navHostController)
            TODO("Llamar al screen de Splash cuando se cree")
        }

    }

}


sealed class IntroduceScreens(val route: String){
    object Presentation: IntroduceScreens("presentation")
    object Splash: IntroduceScreens("splash")
}