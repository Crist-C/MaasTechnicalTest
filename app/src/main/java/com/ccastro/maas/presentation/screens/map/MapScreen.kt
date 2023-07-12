package com.ccastro.maas.presentation.screens.map

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.ccastro.maas.presentation.screens.map.components.MapContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    Scaffold(
        topBar = {},
        content = {
            MapContent()
        },
        bottomBar = {}
    )
}

