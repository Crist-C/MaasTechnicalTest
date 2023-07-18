package com.ccastro.maas.presentation.screens.addusercard.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.presentation.components.DefaultCircularProgress
import com.ccastro.maas.presentation.navigation.HomeNavigationScreens
import com.ccastro.maas.presentation.screens.addusercard.AddUserCardViewModel

@Composable
fun AddUserCard(navHostController: NavHostController, viewModel: AddUserCardViewModel = hiltViewModel()){
    val state = viewModel.state
    when(viewModel.addUserCardResponse){
        is Response.Fail -> {
            viewModel.enabledSaveButton()
        }
        Response.Loading -> {
            DefaultCircularProgress()
        }
        is Response.Success -> {
            LaunchedEffect(Unit) {
                navHostController.navigate(HomeNavigationScreens.Home.route)
            }
        }

        else -> {}
    }

    if(state.showToast){
        Toast.makeText(LocalContext.current, state.userMessage, Toast.LENGTH_LONG).show()
        viewModel.resetValues()
    }

}
