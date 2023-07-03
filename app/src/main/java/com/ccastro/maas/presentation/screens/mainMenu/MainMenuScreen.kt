package com.ccastro.maas.presentation.screens.mainMenu

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ccastro.maas.domain.StoppingPlace
import com.ccastro.maas.domain.UserCard
import com.ccastro.maas.presentation.screens.mainMenu.components.MainMenuContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(navHostController: NavHostController, userCards: List<UserCard> = listOf(UserCard()), stoppingPlace: List<StoppingPlace> = listOf(
    StoppingPlace()
)) {

    Scaffold(
        topBar = {},
        content = {
                  MainMenuContent(userCards, stoppingPlace, navHostController)
        },
        bottomBar = {}
    )
}
