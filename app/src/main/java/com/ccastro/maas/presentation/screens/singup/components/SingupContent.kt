package com.ccastro.maas.presentation.screens.singup.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
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
import com.ccastro.maas.presentation.components.DefaultButton
import com.ccastro.maas.presentation.components.DefaultEnunciado
import com.ccastro.maas.presentation.components.DefaultTextField
import com.ccastro.maas.presentation.components.LogoMaasComponent
import com.ccastro.maas.presentation.navigation.AppScreens
import com.ccastro.maas.presentation.screens.singup.SingupViewModel
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun SingupContent(navHostController: NavHostController, viewModel: SingupViewModel = hiltViewModel(),) {

    val singupFlow = viewModel.singupFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LogoMaasComponent(modifier = Modifier.padding(bottom = 0.dp))
        SingupFieldsCard(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp),
            navHostController = navHostController
        )
    }

    singupFlow.value.let {
        when(it){
            is Response.Fail ->{
                Toast.makeText(LocalContext.current, it.exception?.message, Toast.LENGTH_LONG).show()
            }
            Response.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            is Response.Success -> {
                LaunchedEffect(Unit){
                    navHostController.popBackStack(AppScreens.Login.route, true)
                    navHostController.navigate(AppScreens.Login.route){
                        popUpTo(AppScreens.Singup.route) {inclusive = true}
                    }
                }
            }
            else -> {}
        }
    }

}

@Composable
fun SingupFieldsCard(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: SingupViewModel = hiltViewModel(),
){

    Surface(
        modifier = modifier
            .wrapContentSize(),
        shape = ShapeDefaults.Small,
        shadowElevation = 8.dp,
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultEnunciado(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp),
                titleText = "REGISTRO",
                titleStyle = MaterialTheme.typography.headlineMedium,
                sentenceText = "Por favor ingresa tus datos para continuar",
                sentenceStyle = MaterialTheme.typography.titleSmall
            )
            DefaultTextField(
                label = "Nombre completo",
                icon = Icons.Default.Email,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = viewModel.name.value,
                onValueChange = {
                    viewModel.name.value = it
                    viewModel.validateUserName()
                },
                errorMsg = viewModel.nameErrorMsg.value
            )
            DefaultTextField(
                label = "Correo electrónico",
                icon = Icons.Default.Email,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = viewModel.email.value,
                onValueChange = {
                    viewModel.email.value = it
                    viewModel.validateEmail()
                },
                errorMsg = viewModel.emailErrorMsg.value
            )
            DefaultTextField(
                label = "Password",
                icon = Icons.Default.Email,
                hideText = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = viewModel.password.value,
                onValueChange = {
                    viewModel.password.value = it
                    viewModel.validatePassword()
                },
                errorMsg = viewModel.passwordErrorMsg.value
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = "Confirmar Password",
                icon = Icons.Default.Email,
                hideText = true,
                value = viewModel.passwordValidate.value,
                onValueChange = {
                    viewModel.passwordValidate.value = it
                    viewModel.validatePasswordConfirm()
                },
                errorMsg = viewModel.passwordValidateErrorMsg.value
            )
            DefaultButton(
                modifier = Modifier.padding(start = 28.dp, end = 28.dp, top = 28.dp),
                text = "REGISTRARME",
                onClick ={
                    viewModel.onSignup()
                },
                enable = viewModel.isEnabledSingupButton
            )
            DefaultButton(
                modifier = Modifier.padding(start = 28.dp, end = 28.dp, bottom = 28.dp),
                text = "INICIAR SESIÓN",
                colors = ButtonDefaults.outlinedButtonColors(),
                onClick = {
                    navHostController.popBackStack()
                }
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SingupContentPreview() {
    MaasTheme() {
        SingupContent(navHostController = rememberNavController())
    }
}