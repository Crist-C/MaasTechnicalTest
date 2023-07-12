package com.ccastro.maas.presentation.screens.home


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.ConfirmOptions
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.domain.use_cases.stopPlaces.StopPlacesUseCases
import com.ccastro.maas.domain.use_cases.userCard.UserCardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

// Al ViewModel se le injectan la clase que contiene los casos de uso

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userCardUseCases: UserCardUseCases,
    private val stopPlacesUseCases: StopPlacesUseCases,
        authUseCases: AuthUseCases) : ViewModel() {

    var state: MutableState<HomeState> = mutableStateOf(HomeState())

    init {
        viewModelScope.launch {
            userCardUseCases.getAllUserCards(authUseCases.getCurrentUser()!!.uid).collectLatest {
                state.value.userCards.value = it
            }

        }
    }


    fun actualizarRutas() {
            viewModelScope.launch {
                try {
                    runBlocking {
                        val task = async { stopPlacesUseCases.getNearStopPlaces(
                            latitude = state.value.lat,
                            longitud = state.value.lon,
                            radius = state.value.rad
                        ) }
                        val stopList = task.await()
                        state.value.stopPlaces.value = stopList
                    }


                }catch (e: Exception){
                    Log.e("MLOG","ExceptionRadList ${e.message}")
                }
            }
        }

    fun onDialogConfirm() {
        when (state.value.confirmFunction) {
            ConfirmOptions.Eliminar.option -> deleteUserCardOnDB()
        }
        state.value.confirmFunction = ""
        state.value.showDialog.value = false
    }

    fun onDialogDismiss() {
        state.value.confirmFunction = ""
        state.value.showDialog.value = false
    }

    fun openDialog() {
        state.value.showDialog.value = true
    }

    fun deleteUserCard(userCard: UserCard) {
        if (userCard.card != "") {
            state.value.titleDialog = "Eliminar tarjeta"
            state.value.textDialog = "Desea eliminar la tarjeta: \n${userCard.cardNumber}?"
            state.value.confirmFunction = ConfirmOptions.Eliminar.option
            state.value.currentCardUser = userCard
            openDialog()
        } else {
            state.value.titleDialog = "Aun no tienes una tarjeta"
            state.value.textDialog = "¿deseas agregar una tarjeta?"
            state.value.confirmFunction = ConfirmOptions.Agregar.option
            openDialog()
        }
    }

    fun deleteUserCardOnDB() {
        viewModelScope.launch(Dispatchers.IO) {
            userCardUseCases.deleteCard(state.value.currentCardUser)
            state.value.currentCardUser = UserCard()
        }
    }

}