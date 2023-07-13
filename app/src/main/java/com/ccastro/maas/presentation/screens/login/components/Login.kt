package com.ccastro.maas.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.presentation.components.DefaultCircularProgress
import com.ccastro.maas.presentation.navigation.AppScreens
import com.ccastro.maas.presentation.screens.login.LoginViewModel

@Composable
fun Login(navHostController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    val state = viewModel.state

    when(val loginResponse = viewModel.loginResponse){
        Response.Loading -> {
            state.isEnabledLoginButton = false
            DefaultCircularProgress()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                navHostController.navigate(AppScreens.Home.route){
                    popUpTo(AppScreens.Login.route) {inclusive = true}
                }
            }
        }
        is Response.Fail -> {
            state.isEnabledLoginButton = true
            Toast.makeText(LocalContext.current, loginResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }


}