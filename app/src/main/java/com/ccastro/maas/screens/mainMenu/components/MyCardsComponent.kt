package com.ccastro.maas.screens.mainMenu.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccastro.maas.StoppingPlace
import com.ccastro.maas.UserCard
import com.ccastro.maas.ui.theme.MaasTheme

@Composable
fun MyCardsComponent(
    userCards: List<UserCard> = listOf(UserCard()),
    stoppingPlaces: List<StoppingPlace> = listOf(
        StoppingPlace()
    ),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Mis Tarjetas",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
        BoxWithConstraints {
            UserCardListComponent(userCardList = userCards)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 170.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                ButtonDelete()
                Spacer(modifier = Modifier.padding(4.dp))
                ButtonAdd()
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyCardsComponentPreview() {
    MaasTheme {
        MyCardsComponent()
    }
}