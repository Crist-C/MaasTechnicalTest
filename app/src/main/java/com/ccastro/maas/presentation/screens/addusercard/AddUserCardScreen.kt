package com.ccastro.maas.presentation.screens.addusercard

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.presentation.screens.addusercard.components.AddUserCard
import com.ccastro.maas.presentation.screens.addusercard.components.AddUserCardContent
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddUserCardScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {},
        content = {
            AddUserCardContent(navHostController)
        },
        bottomBar = {}
    )
    AddUserCard(navHostController = navHostController)
}

@Preview
@Composable
fun AddUserCardScreenPreview(){
    MaasTheme {
        AddUserCardScreen(rememberNavController())
    }
}