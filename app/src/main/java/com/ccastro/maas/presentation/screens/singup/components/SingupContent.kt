package com.ccastro.maas.presentation.screens.singup.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.presentation.components.DefaultButton
import com.ccastro.maas.presentation.components.DefaultEnunciado
import com.ccastro.maas.presentation.components.DefaultTextField
import com.ccastro.maas.presentation.components.LogoMaasComponent
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun SingupContent(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoMaasComponent(modifier = Modifier.padding(bottom = 0.dp))
        SingupFieldsCard(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp),
            navHostController = navHostController
        )
    }
}

@Composable
fun SingupFieldsCard(modifier: Modifier = Modifier, navHostController: NavHostController){
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
                titleText = "REGISTRO",
                titleStyle = MaterialTheme.typography.headlineMedium,
                sentenceText = "Por favor ingresa tus datos para continuar",
                sentenceStyle = MaterialTheme.typography.titleSmall
            )
            DefaultTextField(
                modifier = Modifier,
                value = "",
                onValueChange = {},
                label = "Nombre de usuario",
                icon = Icons.Default.Email
            )
            DefaultTextField(
                modifier = Modifier,
                value = "",
                onValueChange = {},
                label = "Correo electrónico",
                icon = Icons.Default.Email
            )
            DefaultTextField(
                modifier = Modifier,
                value = "",
                onValueChange = {},
                label = "Password",
                icon = Icons.Default.Email
            )
            DefaultTextField(
                modifier = Modifier,
                value = "",
                onValueChange = {},
                label = "Confirmar Password",
                icon = Icons.Default.Email
            )
            DefaultButton(
                modifier = Modifier.padding(start = 28.dp, end = 28.dp, top = 28.dp),
                text = "REGISTRARME",
                onClick = { /*TODO*/ }
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