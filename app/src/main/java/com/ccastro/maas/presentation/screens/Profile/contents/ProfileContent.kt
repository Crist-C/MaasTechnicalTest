package com.ccastro.maas.presentation.screens.Profile.contents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.presentation.components.DefaultButton
import com.ccastro.maas.presentation.navigation.AppScreens
import com.ccastro.maas.presentation.screens.Profile.ProfileViewModel
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun ProfileContent(navHostController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp),
            RoundedCornerShape(topStartPercent = 10, topEndPercent = 10, bottomStartPercent = 10, bottomEndPercent = 10),
            shadowElevation = 8.dp,

            ) {
            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp),
                    contentScale = ContentScale.Crop,
                    imageVector = Icons.Default.Person,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surfaceTint),
                    contentDescription = "User image"
                )
                Text(
                    modifier = Modifier.padding(),
                    text = "User Name",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )
                DefaultButton(
                    modifier = Modifier.padding(25.dp),
                    text = "LogOut",
                    onClick = {
                        viewModel.logout()
                        navHostController.popBackStack(AppScreens.Login.route, true)
                        navHostController.navigate(route = AppScreens.Login.route){
                            popUpTo(route = AppScreens.Profile.route){inclusive = true}
                        }
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileContentPreview(){
    MaasTheme {
        ProfileContent(rememberNavController())
    }
}
