package com.ccastro.maas.presentation.screens.map.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapContent() {
    Box(modifier = Modifier.fillMaxSize()){
        GoogleMap(modifier = Modifier.fillMaxSize())
        Text(text = "MY FIRST MAPA", style = MaterialTheme.typography.headlineMedium)
    }
}