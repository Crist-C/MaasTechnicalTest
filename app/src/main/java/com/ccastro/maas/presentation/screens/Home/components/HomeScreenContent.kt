package com.ccastro.maas.presentation.screens.Home.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.domain.StoppingPlace
import com.ccastro.maas.domain.UserCard
import com.ccastro.maas.presentation.components.LogoMaasComponent
import com.ccastro.maas.presentation.ui.theme.MaasTheme

/**
 *  LOS CONTENT SON LOS MÉTODOS QUE CONTIENEN LA TOTALIDAD DE LA INTERFAZ
 */
@Composable
fun HomeScreenContent(
    userCards: List<UserCard> = listOf(UserCard()),
    stoppingPlaces: List<StoppingPlace> = listOf(StoppingPlace()),
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeHead()
        HomeBody(
            modifier = Modifier.weight(0.8f),
            userCards,
            stoppingPlaces,
            navController
        )
    }
}

@Composable
fun HomeHead() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LogoMaasComponent( modifier = Modifier
                .size(size = 100.dp)
                .padding(horizontal = 14.dp)
        )
    }

}

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    userCards: List<UserCard> = listOf(UserCard()),
    stoppingPlaces: List<StoppingPlace> = listOf(StoppingPlace()),
    navController: NavHostController
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
        MyCardsComponent(userCards, navController = navController)
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        MyNearStoppingComponent(stoppingPlaces)
    }

}


//                      PREVIEWS

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeContentPreview() {
    MaasTheme {
        HomeScreenContent(demoCardList(), demoStoppinPlaceList(), rememberNavController())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeHeadPreview() {
    MaasTheme {
        HomeHead()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeBodyPreview() {
    MaasTheme {
        HomeBody(navController = rememberNavController())
    }
}




