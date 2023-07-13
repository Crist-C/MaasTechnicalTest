package com.ccastro.maas.presentation.screens.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginContentPreview(){
    MaasTheme {
        LoginContent(rememberNavController())
    }
}
