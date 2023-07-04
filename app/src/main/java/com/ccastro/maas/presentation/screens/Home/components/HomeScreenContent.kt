package com.ccastro.maas.presentation.screens.Home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.ccastro.maas.domain.model.StoppingPlace
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.presentation.components.LogoMaasComponent
import com.ccastro.maas.presentation.navigation.AppScreens
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
        HomeHead(navController)
        HomeBody(
            modifier = Modifier.weight(0.8f),
            userCards,
            stoppingPlaces,
            navController
        )
    }
}

@Composable
fun HomeHead(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LogoMaasComponent( modifier = Modifier
                .size(size = 100.dp)
                .padding(horizontal = 0.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth().matchParentSize().padding(bottom = 2.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .padding(horizontal = 14.dp),
                onClick = {
                    navController.navigate(AppScreens.Profile.route){
                        //popUpTo(AppScreens.Home.route){ inclusive = true}
                    }
                }
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(1.dp, MaterialTheme.colorScheme.surfaceTint, CircleShape),
                    imageVector = Icons.Default.Person,
                    contentDescription = "Icon profile",
                    tint = MaterialTheme.colorScheme.surfaceTint)
            }
        }

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
        HomeHead(navController = rememberNavController())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeBodyPreview() {
    MaasTheme {
        HomeBody(navController = rememberNavController())
    }
}




