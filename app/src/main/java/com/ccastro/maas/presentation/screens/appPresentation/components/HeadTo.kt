package com.ccastro.maas.presentation.screens.appPresentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccastro.maas.presentation.navigation.Graph
import com.ccastro.maas.presentation.screens.appPresentation.AppPresentationViewModel

@Composable
fun HeadTo(navHostController: NavHostController, viewModel: AppPresentationViewModel = hiltViewModel()){
    val state = viewModel.state

    state.nextRoute?.let {
        navHostController.navigate(it){
            navHostController.popBackStack()
            popUpTo(Graph.INTRODUCE)
        }
    }

}