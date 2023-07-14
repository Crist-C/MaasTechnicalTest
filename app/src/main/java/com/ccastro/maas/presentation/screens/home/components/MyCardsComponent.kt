package com.ccastro.maas.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.presentation.navigation.HomeNavigationScreens
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun MyCardsComponent(
    navController: NavHostController,
) {

    Surface(
        modifier = Modifier.wrapContentSize(),
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(topStartPercent = 0, bottomStartPercent = 0)
    ) {
        Column(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 4.dp),
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
                UserCardListComponent()
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 170.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    Spacer(modifier = Modifier.padding(4.dp))
                    ButtonAdd { navController.navigate(route = HomeNavigationScreens.AddUserCard.route) }
                }

            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyCardsComponentPreview() {
    MaasTheme {
        MyCardsComponent(navController = rememberNavController())
    }
}