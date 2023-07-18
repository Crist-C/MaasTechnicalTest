package com.ccastro.maas.presentation.screens.addusercard

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.domain.use_cases.userCard.UserCardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddUserCardViewModel @Inject constructor(private val userCardUseCases: UserCardUseCases, private val authUseCases: AuthUseCases) : ViewModel() {

    var state by mutableStateOf(AddUserCardState())
        private set

    var addUserCardResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    fun addUserCardFlow() = viewModelScope.launch{

        // Mostramos el simbolo de carga
        addUserCardResponse = Response.Loading
        loadingValues()

        val currentUserId = authUseCases.getCurrentUser()!!.uid

        val cardExist = withContext(Dispatchers.Default) {
            userCardUseCases.verifyIfCardExistInDB(
                state.cardNumberInputUser,
                currentUserId
            )
        }

        if (cardExist == true){
            state.userMessage = "La tarjeta ya la tienes agregada"
            state = state.copy(showToast = true)
            cancel()
        }

        // Lanzamos la primera tarea asincrona
        val validateCardTask = async { userCardUseCases.addUserCard.validateCard(state.cardNumberInputUser) }
        // esperamos que finalice su ejecución para obtener el resultado
        state.userCardBi = validateCardTask.await()!!


        // Ejecutamos la lógica
        if (state.userCardBi.isValid == true){

            // Lanzamos la segunda tarea asincrona
            val getInfoCardTask = async { userCardUseCases.addUserCard.getFullInfoCard(state.cardNumberInputUser) }
            // esperamos que finalice su ejecución para obtener el resultado
            state.userCardFi = getInfoCardTask.await()

            // ejecutamos la lógica de negocio
            if (state.userCardFi.cardNumber.isDigitsOnly()){

                // Completamos la información de una tarjeta con la otra y le adicionamos el UserId que le asigna FirebaseAuth
                userCardUseCases.addUserCard.completeCardData(state.userCardFi, state.userCardBi, currentUserId)
                // Almacenamos la tarjeta en la BD Local
                userCardUseCases.saveCard(state.userCardFi)

                addUserCardResponse = Response.Success(true, message = "¡Ya vinculaste tu tarjeta 😄!", wasSuccess = true)
                state.userMessage = "¡Ya vinculaste tu tarjeta 😄!"
            }else{
                addUserCardResponse = Response.Fail( null, "Esa tarjeta no es válida")
                state.userMessage = "Esa tarjeta no es válida"
            }
        }else{
            addUserCardResponse = Response.Fail(null, "La tarjeta no está activa")
            state.userMessage = "La tarjeta no está activa"
        }
        state = state.copy(showToast = true)
        Log.e("MLOG", "Info(_userCardBi): ${state.userCardBi}")
        Log.e("MLOG", "Info(cardNumerUserInput): ${state.cardNumberInputUser}")
        Log.i("MLOG", "Info(cardValidationConsult, addUserCardVM): $addUserCardResponse")

    }

    // Validaciones de los datos

    fun setFormatToCardNumber(cardNumber: String) {
        if(cardNumber.trim().length <= 16 && cardNumber.trim().isDigitsOnly()){
            state.cardNumberInputUser = cardNumber.trim()
            addSpacesToCardNumber(
                when (cardNumber.trim()){
                    "" -> "0000000000000000"
                    else -> cardNumber.trim()
                })
        }
    }

    private fun addSpacesToCardNumber(text: String){
        val stringBuilder = StringBuilder(text)
        var index = 4
        while (index < stringBuilder.length) {
            stringBuilder.insert(index, ' ')
            index += 5
        }
        state = state.copy(cardNumberOnImage = stringBuilder.toString())
    }

    fun enabledSaveButton() {
        val isEnabledSaveButton= state.cardNumberInputUser.length == 16 && state.cardNumberInputUser.isDigitsOnly()
        state = state.copy(isEnabledSaveButton = isEnabledSaveButton)
    }

    private fun loadingValues(){
        state = state.copy( isEnabledSaveButton = false)
        state = state.copy( isEnabledTextInput = false)
    }

    fun resetValues(){
        addUserCardResponse = null
        state = state.copy( isEnabledTextInput = true, showToast = false)
    }

}