package com.ccastro.maas.presentation.screens.Map

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ccastro.maas.presentation.screens.Map.components.MapContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navHostController: NavHostController){
    Scaffold(
        topBar = {},
        content = {
            MapContent(navHostController)
        },
        bottomBar = {}
    )
}

