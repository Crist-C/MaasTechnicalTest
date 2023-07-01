package com.ccastro.maas.screens.mainMenu.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccastro.maas.R
import com.ccastro.maas.ui.theme.MaasTheme

@Composable
fun ButtonDelete() {
    val image = painterResource(id = R.drawable.icon_delete_24)

    Surface(
        modifier = Modifier
            .wrapContentSize(),
        shape = MaterialTheme.shapes.extraLarge,
        shadowElevation = 6.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(50.dp),
                shape = CircleShape
            ) {}
            Column(
                modifier = Modifier.size(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = image, contentDescription = "Add card tu llave icon",
                    modifier = Modifier.size(40.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ButtonDeletePreview() {
    MaasTheme {
        ButtonAdd()
    }
}