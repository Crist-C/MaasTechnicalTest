package com.ccastro.maas.presentation.screens.home


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.DialogContents
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
    private val authUseCases: AuthUseCases) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            userCardUseCases.getAllUserCards(authUseCases.getCurrentUser()!!.uid).collectLatest {
                state = state.copy(userCards = it)
            }
        }
    }

    fun actualizarRutas() {
        viewModelScope.launch {
            try {
                runBlocking {
                    val task = async {
                        stopPlacesUseCases.getNearStopPlaces(latitude = state.lat, longitud = state.lon, radius = state.rad)
                    }
                    val stopList = task.await()
                    state.stopPlaces.value = stopList
                }
            }catch (e: Exception){
                Log.e("MLOG","ExceptionRadList ${e.message}")
            }
        }
    }

    fun validateDialogContent(userCard: UserCard){
        if(userCard == UserCard()){
            state.dialogContent = DialogContents.Agregar
            (state.dialogContent as DialogContents.Agregar).onCallFunction = {}
        }else{
            state.currentCardUser = userCard
            state.dialogContent = DialogContents.Eliminar
            (state.dialogContent as DialogContents.Eliminar).onCallFunction = {deleteUserCardOnDB()}
        }
        openDialog()
    }

    private fun openDialog() {
        state = state.copy(showDialog = true)
    }

    fun onDialogConfirm() {
        state.dialogContent = null
        state = state.copy(showDialog = false)
    }

    fun onDialogDismiss() {
        state.dialogContent = null
        state = state.copy(showDialog = false)
    }

    private fun deleteUserCardOnDB() {
        viewModelScope.launch(Dispatchers.IO) {
            userCardUseCases.deleteCard(state.currentCardUser)
            state.currentCardUser = UserCard()
        }
    }

}