package com.ccastro.maas.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.presentation.screens.login.components.LoginContent
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController) {

    val viewModel = LoginViewModel()

    Scaffold(
        topBar = {},
        content = {
            LoginContent(viewModel, navHostController)
        },
        bottomBar = {}
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaasTheme() {
        LoginScreen(navHostController = rememberNavController())
    }
}