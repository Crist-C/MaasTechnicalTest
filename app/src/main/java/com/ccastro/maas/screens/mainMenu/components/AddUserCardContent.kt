package com.ccastro.maas.screens.mainMenu.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ccastro.maas.ui.theme.MaasTheme

@Composable
fun AddUserCardContent (modifier: Modifier = Modifier){
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier,
            text = "Agrega una tarjeta",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddUserCardContentPreview(){
    MaasTheme {
        AddUserCardContent()
    }
}