package com.ccastro.maas.presentation.screens.appPresentation


import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ccastro.maas.presentation.screens.appPresentation.components.AppPresentationContent
import com.ccastro.maas.presentation.screens.appPresentation.components.HeadTo

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppPresentationScreen(navHostController: NavHostController) {
    Scaffold (
        topBar = {},
        content = {
            AppPresentationContent()
        },
        bottomBar = {}
    )
    HeadTo(navHostController = navHostController)
}