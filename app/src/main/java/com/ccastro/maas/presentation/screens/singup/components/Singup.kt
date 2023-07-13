package com.ccastro.maas.presentation.screens.singup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.presentation.components.DefaultCircularProgress
import com.ccastro.maas.presentation.navigation.AppScreens
import com.ccastro.maas.presentation.screens.singup.SingupViewModel

@Composable
fun Singup(navHostController: NavHostController, viewModel: SingupViewModel = hiltViewModel()){
    when(val singupResponse = viewModel.singupResponse){
        is Response.Fail ->{
            Toast.makeText(LocalContext.current, singupResponse.exception?.message, Toast.LENGTH_LONG).show()
        }
        Response.Loading -> {
            DefaultCircularProgress()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                viewModel.createUser()
                navHostController.popBackStack(AppScreens.Login.route, true)
                navHostController.navigate(AppScreens.Login.route){
                    popUpTo(AppScreens.Singup.route) {inclusive = true}
                }
            }
        }
        else -> {}
    }
}