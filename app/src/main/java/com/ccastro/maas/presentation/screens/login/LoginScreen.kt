package com.ccastro.maas.presentation.screens.login

import android.annotation.SuppressLint
import android.view.WindowManager
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.presentation.screens.login.components.LoginContent
import com.ccastro.maas.presentation.components.EditKeyBoardUiMode
import com.ccastro.maas.presentation.screens.login.components.Login
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController) {
    EditKeyBoardUiMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

    Scaffold(
        topBar = {},
        content = {
            LoginContent(navHostController = navHostController)
        },
        bottomBar = {}
    )
    Login(navHostController = navHostController)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaasTheme() {
        LoginScreen(navHostController = rememberNavController())
    }
}