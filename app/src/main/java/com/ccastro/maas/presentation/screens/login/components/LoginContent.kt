package com.ccastro.maas.presentation.screens.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
fun LoginContent (navHostController: NavHostController){
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
}

@Composable
fun LogingFieldsCard(modifier: Modifier = Modifier){
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
                label = "Contraseña",
                icon = Icons.Default.Email
            )
            DefaultButton(
                modifier = Modifier.padding(horizontal = 28.dp, vertical = 28.dp),
                text = "INICIAR SESIÓN",
                onClick = { /*TODO*/ }
            )
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
