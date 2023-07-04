package com.ccastro.maas.presentation.screens.Home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ccastro.maas.domain.model.StoppingPlace
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.presentation.screens.Home.components.HomeScreenContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController, userCards: List<UserCard> = listOf(UserCard()), stoppingPlace: List<StoppingPlace> = listOf(
    StoppingPlace()
)) {

    Scaffold(
        topBar = {
        },
        content = {
            HomeScreenContent(userCards, stoppingPlace, navHostController)
        },
        bottomBar = {}
    )
}
