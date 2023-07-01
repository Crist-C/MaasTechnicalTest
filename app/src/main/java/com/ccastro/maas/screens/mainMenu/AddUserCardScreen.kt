package com.ccastro.maas.screens.mainMenu

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.ccastro.maas.screens.mainMenu.components.AddUserCardContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddUserCardScreen() {
    Scaffold(
        topBar = {},
        content = {
            AddUserCardContent()
        },
        bottomBar = {}
    )
}