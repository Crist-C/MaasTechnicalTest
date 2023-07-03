package com.ccastro.maas.presentation.screens.singup

import android.annotation.SuppressLint
import android.view.WindowManager
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.presentation.components.EditKeyBoardUiMode
import com.ccastro.maas.presentation.screens.singup.components.SingupContent
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingupScreen(navHostController: NavHostController) {
    @Suppress("DEPRECATION")
    EditKeyBoardUiMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    Scaffold (
        topBar = {
        },
        content = {
                  SingupContent(navHostController)
        },
        bottomBar = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SingupScreenPreview(){
    MaasTheme() {
        SingupScreen(navHostController = rememberNavController())
    }
}