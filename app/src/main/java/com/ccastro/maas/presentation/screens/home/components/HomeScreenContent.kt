package com.ccastro.maas.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.domain.model.DialogContents
import com.ccastro.maas.presentation.components.DefaultAlertDialog
import com.ccastro.maas.presentation.components.DefaultIconButton
import com.ccastro.maas.presentation.components.LogoMaasComponent
import com.ccastro.maas.presentation.navigation.HomeNavigationScreens
import com.ccastro.maas.presentation.screens.home.HomeViewModel
import com.ccastro.maas.presentation.ui.theme.MaasTheme

/**
 *  LOS CONTENT SON LOS MÉTODOS QUE CONTIENEN LA TOTALIDAD DE LA INTERFAZ
 */
@Composable
fun HomeScreenContent(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    Box(modifier = Modifier.fillMaxSize()){
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
                navController
            )
        }
        DefaultAlertDialog(
            showDialog = state.showDialog,
            onConfirm = {
                if(state.dialogContent is DialogContents.Eliminar)
                {
                    state.dialogContent?.onCallFunction?.invoke()
                    viewModel.onDialogConfirm()
                }else{
                    viewModel.onDialogDismiss()
                    viewModel.stopLocationRequest()
                    navController.navigate(HomeNavigationScreens.AddUserCard.route)
                }
            },
            onDismiss = { viewModel.onDialogDismiss()},
            title = state.dialogContent?.title ?: "",
            textDialog = state.dialogContent?.description + "\n${state.currentCardUser.card}",
        )
    }
}

@Composable
fun HomeHead(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

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
                .fillMaxWidth()
                .matchParentSize()
                .padding(bottom = 2.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultIconButton(
                modifier = Modifier.padding(14.dp),
                icon = Icons.Default.Person,
                onClick = {
                    viewModel.stopLocationRequest()
                    navHostController.navigate(HomeNavigationScreens.Profile.route)
                }
            )

        }

    }


}

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Hola!, ¿hacia dónde vas?",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black
        )
        MyCardsComponent(navController = navController)
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        MyNearStoppingComponent(navHostController = navController)
    }

}


//                      PREVIEWS

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeContentPreview() {
    MaasTheme {
        HomeScreenContent(
            //demoStoppinPlaceList(),
            rememberNavController()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeHeadPreview() {
    MaasTheme {
        HomeHead(navHostController = rememberNavController())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeBodyPreview() {
    MaasTheme {
        HomeBody(navController = rememberNavController())
    }
}




