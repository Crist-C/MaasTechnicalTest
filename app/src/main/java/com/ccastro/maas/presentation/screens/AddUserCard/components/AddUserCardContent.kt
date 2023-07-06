package com.ccastro.maas.presentation.screens.AddUserCard.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.maas.R
import com.ccastro.maas.presentation.components.DefaultButton
import com.ccastro.maas.presentation.components.DefaultEnunciado
import com.ccastro.maas.presentation.components.DefaultIconButton
import com.ccastro.maas.presentation.components.DefaultTextField
import com.ccastro.maas.presentation.navigation.AppScreens
import com.ccastro.maas.presentation.screens.AddUserCard.AddUserCardViewModel
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@Composable
fun AddUserCardContent (navHostController: NavHostController, viewModel: AddUserCardViewModel = hiltViewModel()){

    val addUserCardFlow = viewModel.addUserCardFlow.collectAsState()

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            DefaultIconButton(
                modifier = Modifier.padding(14.dp),
                onClick = {
                    navHostController.navigate(AppScreens.Home.route)
                }
            )
        }

        DefaultEnunciado(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 24.dp),
            titleText = "Agrega una tarjeta",
            titleStyle = MaterialTheme.typography.titleLarge,
            sentenceText = "Agrega una de tus tarjetas para que puedas ver su saldo y aplicar una recarga",
            sentenceStyle = MaterialTheme.typography.titleSmall
        )

        UserCardBackViewComponent()

        DefaultTextField(
            modifier = Modifier.padding(top = 25.dp),
            value = viewModel.cardNumberInputUser.value,
            enable = viewModel.isEnabledTextInput.value,
            onValueChange =
            {
                viewModel.setFormatToCardNumber(it)
                viewModel.enabledSaveButton()
            },
            keyboardType = KeyboardType.Number,
            label = "Card number",
            icon = ImageVector.vectorResource(id = R.drawable.icon_card_24),
        )
        DefaultButton(
            modifier = Modifier.padding(start = 26.dp, end = 26.dp),
            text = "Agregar tarjeta",
            enable = viewModel.isEnabledSaveButton.value,
            onClick = {
                viewModel.cardValidationConsult()
            })
        DefaultButton(
            modifier = Modifier.padding(start = 26.dp, end = 26.dp),
            text = "Agregar con NFC",
            colors = ButtonDefaults.outlinedButtonColors(),
            onClick = { /*TODO*/ })

    }
    viewModel.isLoading.let {
        when(it.value){
            true ->  {
                viewModel.isEnabledSaveButton.value = false
                viewModel.isEnabledTextInput.value = false
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            else -> {}
        }

    }

    addUserCardFlow.value.let {
        if (it != null) {
            when(it.wasSuccess){
                true ->{
                    Toast.makeText(LocalContext.current,  it.resultMsg, Toast.LENGTH_LONG).show()
                    viewModel.resetValues()
                    LaunchedEffect(Unit){
                        navHostController.navigate(AppScreens.Home.route)
                    }
                }
                false ->{
                    Toast.makeText(LocalContext.current,  it.resultMsg, Toast.LENGTH_LONG).show()
                    viewModel.resetValues()
                }

                null -> {

                }
            }
        }
    }

}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddUserCardContentPreview(){
    MaasTheme {
        AddUserCardContent(rememberNavController())
    }
}