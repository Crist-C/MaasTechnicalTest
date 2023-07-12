package com.ccastro.maas.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.ccastro.maas.R
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.presentation.screens.home.HomeViewModel
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun UserCardComponent(userCard: UserCard = UserCard(), viewModel: HomeViewModel = hiltViewModel()) {

    val image = painterResource(id = if (userCard.isValid == true) R.drawable.tullave_card_3 else R.drawable.tullave_card_dont_exist)

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
        ) {
            Image(
                painter = image,
                contentDescription = "card image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.large),
            )
            UserCardInformation(
                userCard = userCard
            )
            Surface(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 10.dp, start = 10.dp),
                shape = MaterialTheme.shapes.extraLarge,
                shadowElevation = 6.dp,
                color = if(isSystemInDarkTheme()) Color.White else MaterialTheme.colorScheme.surface
            ){
                Icon(
                    modifier = Modifier
                        .wrapContentSize().padding(4.dp)
                        .size(36.dp)
                        .border(1.dp, Color.White, CircleShape)
                        .clickable {
                                   viewModel.deleteUserCard(userCard)
                        },
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Icon profile",
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}

@Composable
fun UserCardInformation(userCard: UserCard, modifier: Modifier = Modifier) {
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
            text = "$ " + userCard.amount.toString(),
            modifier = modifier
                .fillMaxSize()
                .weight(0.27f),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            color = if(isSystemInDarkTheme()) Color.White else MaterialTheme.colorScheme.onPrimary
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
            text = userCard.profile,
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
                text = if(userCard.status!! == "Enable") "Activa" else "Inactiva",
                modifier = modifier
            )
        }
    }
}

@Composable
fun UserCardListComponent(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    LazyRow(modifier = Modifier.padding(end = 0.dp)) {
        items(viewModel.state.userCards.value.ifEmpty { listOf(UserCard()) }) { userCard ->
            UserCardComponent(userCard = userCard)
        }
    }
    Spacer(modifier = Modifier.padding(60.dp))
}


//                       DATA DEMOS

fun demoCardList(): List<UserCard> {
    var userCardList: List<UserCard> = listOf()
    for (i in 0..3) {
        userCardList += UserCard(
            cardNumber = "1010 0000 0858 2785",
            userName = "Cristian Kevin Castro Parra",
            profile = "Adulto",
            amount = 10000,
            status = if (i % 2 == 0) "Enable"
            else "inactiva"
        )
    }
    return userCardList
}

//                       PREVIEWS

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun UserCardComponentPreview() {
    MaasTheme {
        UserCardComponent(
            userCard = UserCard(
                cardNumber = "1010 0000 0858 2785",
                userName = "Cristian Kevin Castro Parra",
                profile = "Adulto",
                amount = 100000,
                status = "Enable"
            ),

            )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun UserCardComponentWithoutDataPreview() {
    MaasTheme {
        UserCardComponent()
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun UserCardListPreview() {
    MaasTheme {
        UserCardListComponent()
    }
}
