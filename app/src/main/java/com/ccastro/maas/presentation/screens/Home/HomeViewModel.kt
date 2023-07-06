package com.ccastro.maas.presentation.screens.Home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.ConfirmOptions
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.use_cases.userCard.UserCardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

// Al ViewModel se le injectan la clase que contiene los casos de uso

@HiltViewModel
class HomeViewModel @Inject constructor(private val userCardUseCases: UserCardUseCases) : ViewModel() {

    var showDialog by mutableStateOf(false)
    var titleDialog by mutableStateOf("Eliminar tarjeta")
    var textDialog by mutableStateOf("Desea eliminar la tarjeta?\n")
    var confirmFunction by mutableStateOf("")
    var currentCardUser: UserCard = UserCard()


    var state by mutableStateOf(HomeState())


    init {

        viewModelScope.launch {
            userCardUseCases.getAllCard().collectLatest {
                state = state.copy(
                    userCards = it
                )
            }

        }
    }


    private val _countCardsFlow = MutableStateFlow<Int?>(value = null)
    val countCardsFlow : StateFlow<Int?> = _countCardsFlow
    fun contarTarjetas() = viewModelScope.launch {
        _countCardsFlow.value = userCardUseCases.totalUserCards()
    }


    fun onDialogConfirm(){
        when(confirmFunction){
            ConfirmOptions.Eliminar.option -> deleteUserCardOnDB()
        }
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
            confirmFunction = ConfirmOptions.Eliminar.option
            currentCardUser = userCard
            openDialog()
        } else{
            titleDialog = "Aun no tienes una tarjeta"
            textDialog = "¿deseas agregar una tarjeta?"
            confirmFunction = ConfirmOptions.Agregar.option
            openDialog()
        }
    }

    fun deleteUserCardOnDB(){
        viewModelScope.launch (Dispatchers.IO) {
            userCardUseCases.deleteCard(currentCardUser)
            currentCardUser = UserCard()
        }
    }


}