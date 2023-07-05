package com.ccastro.maas.presentation.screens.Home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ccastro.maas.domain.model.UserCard

// Al ViewModel se le injectan la clase que contiene los casos de uso
class HomeViewModel: ViewModel() {

    var userCards: MutableState<MutableList<UserCard>> = mutableStateOf(mutableListOf(UserCard()))
    var showDialog by mutableStateOf(false)
    var titleDialog by mutableStateOf("Eliminar tarjeta")
    var textDialog by mutableStateOf("Desea eliminar la tarjeta?\n")
    var confirmFunction by mutableStateOf("")


    fun onDialogConfirm(){
        confirmFunction = ""
        showDialog = false
    }

    fun onDialogDismiss(){
        confirmFunction = ""
        showDialog = false
    }

    fun openDialog(){
        showDialog = true
    }

    fun deleteUserCard(userCard: UserCard) {
        if(userCard.card != ""){
            titleDialog ="Eliminar tarjeta"
            textDialog = "Desea eliminar la tarjeta: \n${userCard.cardNumber}?"
            confirmFunction = "eliminar"
            openDialog()
        } else{
            titleDialog = "Aun no tienes una tarjeta"
            textDialog = "¿deseas agregar una tarjeta?"
            confirmFunction = "agregar"
            openDialog()
        }
    }


}