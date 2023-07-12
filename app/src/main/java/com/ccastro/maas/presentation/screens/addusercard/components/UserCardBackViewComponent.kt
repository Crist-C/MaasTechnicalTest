package com.ccastro.maas.presentation.screens.addusercard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ccastro.maas.R
import com.ccastro.maas.presentation.screens.addusercard.AddUserCardViewModel
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun UserCardBackViewComponent(viewModel: AddUserCardViewModel = hiltViewModel()){

    val image = painterResource(id = R.drawable.tullave_card_add_card)

    Surface (
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp),
        shape = MaterialTheme.shapes.large,
        shadowElevation = 6.dp
    ){
        BoxWithConstraints(
            modifier = Modifier.wrapContentSize(),
        ) {
            Image(
                painter = image,
                contentDescription = "card image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large),
            )
            Column(
                modifier = Modifier.matchParentSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(start= 30.dp, bottom = 27.dp),
                    text = viewModel.cardNumber,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.tertiaryContainer
                )
            }
            //UserCardInformation()
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CreateUserCardComponentPreview(){
    MaasTheme() {
        UserCardBackViewComponent()
    }
}