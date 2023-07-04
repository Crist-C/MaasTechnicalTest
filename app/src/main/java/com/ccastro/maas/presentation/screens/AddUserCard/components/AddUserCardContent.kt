package com.ccastro.maas.presentation.screens.AddUserCard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.ccastro.maas.R
import com.ccastro.maas.presentation.components.DefaultButton
import com.ccastro.maas.presentation.components.DefaultEnunciado
import com.ccastro.maas.presentation.components.DefaultTextField
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun AddUserCardContent (){
    var cardNumber by remember {
        mutableStateOf("0000000000000000")
    }

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        DefaultEnunciado(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 68.dp),
            titleText = "Agrega una tarjeta",
            titleStyle = MaterialTheme.typography.titleLarge,
            sentenceText = "Agrega una de tus tarjetas para que puedas ver su saldo y aplicar una recarga",
            sentenceStyle = MaterialTheme.typography.titleSmall
        )

        UserCardBackViewComponent(cardNumber)

        DefaultTextField(
            modifier = Modifier.padding(top = 25.dp),
            value = cardNumber,
            onValueChange = { if(it.trim().length <= 16 && it.trim().isDigitsOnly()) cardNumber = it.trim() },
            keyboardType = KeyboardType.Number,
            label = "Card number",
            icon = ImageVector.vectorResource(id = R.drawable.icon_card_24),
        )
        DefaultButton(
            modifier = Modifier.padding(top =60.dp, start = 26.dp, end = 16.dp),
            text = "Agregar tarjeta",
            onClick = { /*TODO*/ })
        DefaultButton(
            modifier = Modifier.padding(top = 0.dp, start = 26.dp, end = 16.dp),
            text = "Agregar con NFC",
            colors = ButtonDefaults.outlinedButtonColors(),
            onClick = { /*TODO*/ })

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddUserCardContentPreview(){
    MaasTheme {
        AddUserCardContent()
    }
}