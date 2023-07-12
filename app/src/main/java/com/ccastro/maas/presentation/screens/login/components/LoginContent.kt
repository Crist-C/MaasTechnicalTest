package com.ccastro.maas.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.presentation.components.LogoMaasComponent
import com.ccastro.maas.presentation.navigation.AppScreens
import com.ccastro.maas.presentation.screens.login.LoginViewModel
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun LoginContent (navHostController: NavHostController, viewModel: LoginViewModel = hiltViewModel()){

    val loginFlow = viewModel.loginFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 34.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoMaasComponent(modifier = Modifier.padding(bottom = 34.dp))
        LogingFieldsCard(modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp))
        LoginBottonBar(navHostController = navHostController)
    }

    // Coroutina que ejecuta una acción en base a la respuesta del login.
    loginFlow.value.let {
        when(it){
            Response.Loading -> {
                viewModel.isEnabledLoginButton = false
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            is Response.Success -> {
                LaunchedEffect(Unit){
                    navHostController.navigate(AppScreens.Home.route){
                        popUpTo(AppScreens.Login.route) {inclusive = true}
                    }
                }
            }
            is Response.Fail -> {
                viewModel.isEnabledLoginButton = true
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginContentPreview(){
    MaasTheme {
        LoginContent(rememberNavController())
    }
}
