package com.ccastro.maas.presentation.screens.profile.contents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
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
import com.ccastro.maas.presentation.screens.profile.ProfileViewModel
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun ProfileContent(navHostController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    val state = viewModel.state

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
                    text = state.userData.username,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    modifier = Modifier.padding(),
                    text = state.userData.email,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                DefaultButton(
                    modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 25.dp, bottom = 8.dp),
                    text = "Editar datos",
                    icon = Icons.Default.Edit,
                    onClick = {

                    }
                )
                DefaultButton(
                    modifier = Modifier.padding(start = 25.dp, end = 25.dp, bottom = 25.dp),
                    text = "Cerrar Sesion",
                    icon = Icons.Default.Close,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
                    onClick = {
                        viewModel.logout()
                        navHostController.popBackStack(AppScreens.Login.route, true)
                        navHostController.navigate(route = AppScreens.Login.route){
                            navHostController.popBackStack(AppScreens.Home.route, true)
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
