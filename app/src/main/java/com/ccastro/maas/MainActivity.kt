package com.ccastro.maas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ccastro.maas.ui.theme.MaasTheme
import com.ccastro.maas.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainMenu()
                }
            }
        }
    }
}

@Composable
fun MainMenu(modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.logo_maas_dark_theme)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ){

            Image(
                modifier = Modifier
                    .size(size = 100.dp)
                    .padding(horizontal = 14.dp),
                painter = image,
                contentDescription = "Logotipo de la app"
            )
        }
        MainMenuContent(modifier = Modifier.weight(0.8f))
    }

}

@Composable
fun MainMenuContent(modifier: Modifier = Modifier) {
    val defaultTypography = Typography
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Que bueno tenerte de nuevo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black
        )
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 16.dp),
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
            /*
            UserCardComponent(
                UserCard(
                    cardNumber = "1010 0000 0858 2785",
                    userName = "Cristian K. Castro Parra",
                    cardType = "Adulto",
                    amount = 10000,
                    cardStatus = "activa"
                )
            )*/
            DemoCardList()?.let { MyUserCardList(userCardList = it) }
        }
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            Text(
                text = "Estaciones cercanas",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            /*
        StoppingPlaceCardComponent(
            stoppingPlace = StoppingPlace(
                "086A06",
                "Batallon Caldas",
                "AK 50 - CL 15",
                "219",
            )
        )
         */

            DemoStoppinPlaceList()?.let { StoppingPlacesNearList(stoppingPlacesList = it) }
        }
    }

}

//                      CARDS COMPONENTS

@Composable
fun UserCardWhithoutData(modifier: Modifier = Modifier){

    val image = painterResource(id = R.drawable.tullave_card_dont_exist)

    Surface(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp),
        shape = MaterialTheme.shapes.large,
        shadowElevation = 6.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.size(300.dp, 180.dp)
        ){
            Image(
                painter = image,
                contentDescription = "card image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.large),
            )

        }
    }
}

@Composable
fun StoppingPlaceCardComponent(stoppingPlace: StoppingPlace, modifier: Modifier = Modifier){

    Surface(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp),
        shape = MaterialTheme.shapes.extraLarge,
        shadowElevation = 6.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = modifier
                .wrapContentSize()
                .padding(bottom = 4.dp, start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = stoppingPlace.id,
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(bottomStartPercent = 10, bottomEndPercent = 10),
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                    .padding(4.dp),
                color = Color.White
            )
            Text(
                modifier = Modifier,
                text = stoppingPlace.name
            )
            Text(
                modifier = Modifier,
                text = stoppingPlace.addres,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                modifier = Modifier,
                text = stoppingPlace.distance+" m"
            )
        }
    }
}

@Composable
fun UserCardComponent(userCard: UserCard, modifier: Modifier = Modifier){

    val image = painterResource(id = R.drawable.tullave_card_op2)

    Surface(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp),
        shape = MaterialTheme.shapes.large,
        shadowElevation = 6.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.size(300.dp, 180.dp)
        ){
            Image(
                painter = image,
                contentDescription = "card image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.large),
            )
            UserCardData(
                userCard = userCard
            )
        }
    }
}


@Composable
fun UserCardData(userCard: UserCard, modifier: Modifier = Modifier){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 16.dp),
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.23f)
        )
        Text(
            text = "$ "+userCard.amount.toString(),
            modifier = modifier
                .fillMaxSize()
                .weight(0.27f),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            color = Color.White
        )
        Text(
            text = userCard.userName,
            modifier = modifier
                .fillMaxWidth()
                .weight(0.1f),
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Right
        )
        Text(
            text = userCard.cardType,
            modifier = modifier
                .fillMaxWidth()
                .weight(0.1f),
            textAlign = TextAlign.End
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = userCard.cardNumber,
                modifier = modifier
                    .border(1.5.dp, Color.White, CircleShape)
                    .padding(vertical = 4.dp, horizontal = 8.dp),
            )
            Text(
                text = userCard.cardStatus,
                modifier = modifier
            )
        }
    }
}

//                      LIST FUNCTIONS

@Composable
fun MyUserCardList(userCardList: List<UserCard>, modifier: Modifier = Modifier){
    LazyRow(){
        items(userCardList){
            userCard -> UserCardComponent(userCard = userCard)
        }
    }
}

@Composable
fun StoppingPlacesNearList(stoppingPlacesList: List<StoppingPlace>, modifier: Modifier = Modifier){
    LazyRow(){
        items(stoppingPlacesList){
            stoppingPlace->StoppingPlaceCardComponent(stoppingPlace = stoppingPlace)
        }
    }
}

//                      DATA DEMOS

fun DemoCardList() : List<UserCard>? {
    var userCardList : List<UserCard> = listOf()
    for (i in 0..3){
        userCardList += UserCard(
                cardNumber = "1010 0000 0858 2785",
                userName = "Cristian Kevin Castro Parra",
                cardType = "Adulto",
                amount = 10000,
                cardStatus = "activa"
            )
    }
    return userCardList
}

fun DemoStoppinPlaceList() : List<StoppingPlace>{
    var stoppinPlaceList : List<StoppingPlace> = listOf()

    for (i in 0..3){
        stoppinPlaceList += StoppingPlace(
            "086A06",
            "Batallon Caldas",
            "AK 50 - CL 15",
            "219",
        )
    }
    return stoppinPlaceList
}

//                      PREVIEWS

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMenuPreview() {
    MaasTheme {
        MainMenuContent()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMenuImagePreview() {
    MaasTheme {
        MainMenu()
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun StoppinListPreview() {
    MaasTheme {
        DemoStoppinPlaceList()?.let { StoppingPlacesNearList(stoppingPlacesList = it) }
    }

}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun StoppingPlaceCardPreview() {
    MaasTheme {
        StoppingPlaceCardComponent(
            stoppingPlace = StoppingPlace(
                "086A06",
                "Batallon Caldas",
                "AK 50 - CL 15",
                "219",
            )
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun UserCardListPreview() {
    MaasTheme {
        DemoCardList()?.let { MyUserCardList(userCardList = it) }
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun UserCardComponentPreview(){
    MaasTheme {
        UserCardComponent(
            userCard = UserCard(
                cardNumber = "1010 0000 0858 2785",
                userName = "Cristian Kevin Castro Parra",
                cardType = "Adulto",
                amount = 10000,
                cardStatus = "activa"
            )
        )
    }
}
