package com.ccastro.maas.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ccastro.maas.R

@Composable
fun LogoMaasComponent(modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.logo_maas_dark_theme)
    Image(
        modifier = modifier,
        painter = image,
        contentDescription = "Logotipo de la app"
    )
}