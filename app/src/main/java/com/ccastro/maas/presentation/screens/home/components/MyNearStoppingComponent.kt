package com.ccastro.maas.presentation.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ccastro.maas.presentation.components.DefaultButton
import com.ccastro.maas.presentation.screens.home.HomeViewModel
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun MyNearStoppingComponent(
    viewModel: HomeViewModel = hiltViewModel(),
){

    Surface(
        modifier = Modifier.wrapContentSize(),
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(topStartPercent = 0, bottomStartPercent = 0)
    ){
        Column(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 24.dp, start = 4.dp)
                .wrapContentSize()
        ) {
            Text(
                text = "Estaciones cercanas",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            StoppingPlacesNearList()
            DefaultButton(
                modifier = Modifier
                    .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                text = "Ver en el mapa",
                icon = Icons.Default.Place,
                onClick = {
                    viewModel.actualizarRutas()
                //navHostController.navigate(AppScreens.Map.route)
            })
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyNearStoppingsComponentPreview() {
    MaasTheme {
        MyNearStoppingComponent()
    }
}