package com.ccastro.maas.screens.mainMenu.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccastro.maas.R
import com.ccastro.maas.StoppingPlace
import com.ccastro.maas.UserCard
import com.ccastro.maas.ui.theme.MaasTheme

/**
 *  LOS CONTENT SON LOS MÉTODOS QUE CONTIENEN LA TOTALIDAD DE LA INTERFAZ
 */
@Composable
fun MainMenuContent( userCards: List<UserCard> = listOf(UserCard()), stoppingPlaces: List<StoppingPlace> = listOf(StoppingPlace()) ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainMenuHead()
        MainMenuBody(
            modifier = Modifier.weight(0.8f),
            userCards,
            stoppingPlaces
        )
    }
}

@Composable
fun MainMenuHead() {
    val image = painterResource(id = R.drawable.logo_maas_dark_theme)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Image(
            modifier = Modifier
                .size(size = 100.dp)
                .padding(horizontal = 14.dp),
            painter = image,
            contentDescription = "Logotipo de la app"
        )
    }

}

@Composable
fun MainMenuBody(
    modifier: Modifier = Modifier,
    userCards: List<UserCard> = listOf(UserCard()),
    stoppingPlaces: List<StoppingPlace> = listOf(StoppingPlace()),
) {

    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Que bueno tenerte de nuevo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black
        )
        MyCardsComponent(userCards)
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        MyNearStoppingComponent(stoppingPlaces)
    }

}


//                      PREVIEWS

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMenuContentPreview() {
    MaasTheme {
        MainMenuContent(DemoCardList(), demoStoppinPlaceList())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMenuHeadPreview() {
    MaasTheme {
        MainMenuHead()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMenuBodyPreview() {
    MaasTheme {
        MainMenuBody()
    }
}




