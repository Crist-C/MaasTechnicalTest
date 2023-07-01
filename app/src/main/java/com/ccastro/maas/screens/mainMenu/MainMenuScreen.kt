package com.ccastro.maas.screens.mainMenu

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.ccastro.maas.StoppingPlace
import com.ccastro.maas.UserCard
import com.ccastro.maas.screens.mainMenu.components.MainMenuContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(userCards: List<UserCard> = listOf(UserCard()), stoppingPlace: List<StoppingPlace> = listOf(StoppingPlace())) {

    Scaffold(
        topBar = {},
        content = {
                  MainMenuContent(userCards, stoppingPlace)
        },
        bottomBar = {}
    )
}
