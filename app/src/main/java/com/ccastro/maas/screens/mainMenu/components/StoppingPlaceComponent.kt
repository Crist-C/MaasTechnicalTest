package com.ccastro.maas.screens.mainMenu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccastro.maas.StoppingPlace
import com.ccastro.maas.ui.theme.MaasTheme

@Composable
fun StoppingPlaceComponent(stoppingPlace: StoppingPlace, modifier: Modifier = Modifier) {

    Surface(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp),
        shape = MaterialTheme.shapes.extraLarge,
        shadowElevation = 6.dp,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .wrapContentSize()
                .padding(bottom = 4.dp, start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stoppingPlace.id,
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(bottomStartPercent = 10, bottomEndPercent = 10),
                        color = MaterialTheme.colorScheme.primary
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
                text = stoppingPlace.distance + " m"
            )
        }
    }
}


//                      DISPLAY LIST OBJECTS FUNCTIONS

@Composable
fun StoppingPlacesNearList(stoppingPlacesList: List<StoppingPlace>) {
    LazyRow {
        items(stoppingPlacesList) { stoppingPlace ->
            StoppingPlaceComponent(stoppingPlace = stoppingPlace)
        }
    }
}

//                      DATA DEMOS

fun demoStoppinPlaceList(): List<StoppingPlace> {
    val stoppinPlaceList: MutableList<StoppingPlace> = mutableListOf()

    for (i in 0..3) {
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

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun StoppingPlaceCardPreview() {
    MaasTheme {
        StoppingPlaceComponent(
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
fun StoppingListPreview() {
    MaasTheme {
        demoStoppinPlaceList().let { StoppingPlacesNearList(stoppingPlacesList = it) }
    }

}