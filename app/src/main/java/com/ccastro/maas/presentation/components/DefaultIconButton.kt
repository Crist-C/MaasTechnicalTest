package com.ccastro.maas.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DefaultIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.ArrowBack,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.surfaceTint
) {
    IconButton(
        modifier = modifier,
        onClick = {
            onClick()
            //navController.navigate(AppScreens.Profile.route){
            }
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, color, CircleShape),
            imageVector = icon,
            contentDescription = "Icon profile",
            tint = color
        )
    }
}