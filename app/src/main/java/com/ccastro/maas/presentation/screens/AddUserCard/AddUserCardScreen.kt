package com.ccastro.maas.presentation.screens.AddUserCard

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ccastro.maas.presentation.screens.AddUserCard.components.AddUserCardContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddUserCardScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {},
        content = {
            AddUserCardContent()
        },
        bottomBar = {}
    )
}