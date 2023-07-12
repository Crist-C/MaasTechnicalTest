package com.ccastro.maas.presentation.screens.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ccastro.maas.presentation.components.DefaultButton
import com.ccastro.maas.presentation.components.DefaultEnunciado
import com.ccastro.maas.presentation.components.DefaultTextField
import com.ccastro.maas.presentation.screens.login.LoginViewModel
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun LogingFieldsCard(modifier: Modifier = Modifier, viewModel: LoginViewModel = hiltViewModel()){

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
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
                titleText = "LOGIN",
                titleStyle = MaterialTheme.typography.headlineMedium,
                sentenceText = "Por favor inicia sesion para continuar",
                sentenceStyle = MaterialTheme.typography.titleSmall
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                value = viewModel.email,
                onValueChange = { viewModel.email = it.trim() },
                label = "Correo electrónico",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                errorMsg = viewModel.emailErrorMsg,
                onValidateData = {viewModel.validateEmail()}
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                value = viewModel.password,
                onValueChange = { viewModel.password = it.trim() },
                label = "Contraseña",
                icon = Icons.Default.Email,
                hideText = true,
                errorMsg = viewModel.passwordErrorMsg,
                onValidateData = {viewModel.validatePassword()}
            )
            DefaultButton(
                modifier = Modifier.padding(horizontal = 28.dp, vertical = 28.dp),
                text = "INICIAR SESIÓN",
                enable = viewModel.isEnabledLoginButton,
                onClick = {
                    viewModel.login()
                }
            )
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogingFieldsCardPreview(){
    MaasTheme() {
        LogingFieldsCard()
    }
}