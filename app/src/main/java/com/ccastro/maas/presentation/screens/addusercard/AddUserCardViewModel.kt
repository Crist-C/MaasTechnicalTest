package com.ccastro.maas.presentation.screens.addusercard

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.domain.use_cases.userCard.UserCardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserCardViewModel @Inject constructor(private val userCardUseCases: UserCardUseCases, private val authUseCases: AuthUseCases) : ViewModel() {

    var userCard: UserCard = UserCard()

    var isEnabledSaveButton by mutableStateOf(false)
    var isEnabledTextInput by mutableStateOf(true)

    var cardNumber by mutableStateOf("0000 0000 0000 0000")
    var cardNumberInputUser by mutableStateOf("1010000008582546")

    var showToast by mutableStateOf(false)

    private var _addUserCardFlow = MutableStateFlow<Response<Boolean>?>(value = null)
    val addUserCardFlow: StateFlow<Response<Boolean>?> = _addUserCardFlow

    private var _userCardBi by mutableStateOf(UserCard())
    private var _userCardFi by mutableStateOf(UserCard())

    lateinit var userMessage: String

    fun cardValidationConsult() = viewModelScope.launch{


        // Mostramos el simbolo de carga
        _addUserCardFlow.value = Response.Loading
        loadingValues()
        val currentUserId = authUseCases.getCurrentUser()!!.uid

        val cardExist = async { userCardUseCases.verifyIfCardExistInDB(cardNumberInputUser, currentUserId) }.await()

        if (cardExist == true){
            userMessage = "La tarjeta ya la tienes agregada"
            showToast= true
            cancel()
        }

        // Lanzamos la primera tarea asincrona
        val validateCardTask = async { userCardUseCases.addUserCard.validateCard(cardNumberInputUser) }
        // esperamos que finalice su ejecución para obtener el resultado
        _userCardBi = validateCardTask.await()!!


        // Ejecutamos la lógica
        if (_userCardBi.isValid == true){

            // Lanzamos la segunda tarea asincrona
            val getInfoCardTask = async { userCardUseCases.addUserCard.getFullInfoCard(cardNumberInputUser) }
            // esperamos que finalice su ejecución para obtener el resultado
            _userCardFi = getInfoCardTask.await()

            // ejecutamos la lógica de negocio
            if (_userCardFi.cardNumber?.isDigitsOnly() == true){

                // Completamos la información de una tarjeta con la otra y le adicionamos el UserId que le asigna FirebaseAuth
                userCardUseCases.addUserCard.completeCardData(_userCardFi, _userCardBi, currentUserId)
                // Almacenamos la tarjeta en la BD Local
                userCardUseCases.saveCard(_userCardFi)

                _addUserCardFlow.value = Response.Success(true, message = "¡Ya vinculaste tu tarjeta 😄!", wasSuccess = true)
                userMessage = "¡Ya vinculaste tu tarjeta 😄!"
            }else{
                _addUserCardFlow.value = Response.Fail( null, "Tarjeta no válida o tuviste un fallo de conexión")
                userMessage = "Tarjeta no válida o tuviste un fallo de conexión"
            }
        }else{
            _addUserCardFlow.value = Response.Fail(null, "La tarjeta no está activa")
            userMessage = "La tarjeta no está activa"
        }
        showToast= true
        Log.e("MLOG", "Info(_userCardBi): ${_userCardBi}")
        Log.e("MLOG", "Info(cardNumerUserInput): ${cardNumberInputUser}")
        Log.i("MLOG", "Info(cardValidationConsult, addUserCardVM): ${_addUserCardFlow}")

    }


    // Validaciones de los datos

    fun enabledSaveButton() {
        isEnabledSaveButton=
            cardNumberInputUser.length == 16 && cardNumberInputUser.isDigitsOnly()
    }

    fun setFormatToCardNumber(cardNumber: String) {
        if(cardNumber.trim().length <= 16 && cardNumber.trim().isDigitsOnly()){
            cardNumberInputUser= cardNumber.trim()
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
        cardNumber= stringBuilder.toString()

    }

    fun loadingValues(){
        isEnabledSaveButton= false
        isEnabledTextInput= false
    }

    fun resetValues(){
        _addUserCardFlow.value = null
        isEnabledTextInput= true
    }

}