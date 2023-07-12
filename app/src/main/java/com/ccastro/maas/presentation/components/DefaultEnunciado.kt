package com.ccastro.maas.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ccastro.maas.presentation.ui.theme.MaasTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DefaultEnunciado (
    modifier: Modifier = Modifier,
    titleText: String,
    titleStyle: TextStyle,
    sentenceText: String,
    sentenceStyle: TextStyle,
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = titleText,
            style = titleStyle,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = sentenceText,
            style = sentenceStyle
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultEnunciadoPrevie(){
    MaasTheme() {
        DefaultEnunciado(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            titleText = "Agrega una tarjeta",
            titleStyle = MaterialTheme.typography.titleLarge,
            sentenceText = "Agrega una de tus tarjetas para que puedas ver su saldo y aplicar una recarga",
            sentenceStyle = MaterialTheme.typography.titleSmall
        )

    }
}