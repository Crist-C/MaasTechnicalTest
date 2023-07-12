package com.ccastro.maas.presentation.screens.addusercard

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    var isEnabledSaveButton: MutableState<Boolean> = mutableStateOf(false)
    var isEnabledTextInput: MutableState<Boolean> = mutableStateOf(true)

    var cardNumber: MutableState<String> = mutableStateOf("0000 0000 0000 0000")
    var cardNumberInputUser: MutableState<String> = mutableStateOf("1010000008582546")

    var showToast: MutableState<Boolean> = mutableStateOf(false)

    private val _addUserCardFlow = MutableStateFlow<Response<Boolean>?>(value = null)
    val addUserCardFlow: StateFlow<Response<Boolean>?> = _addUserCardFlow

    private val _userCardBi : MutableState<UserCard?> = mutableStateOf(UserCard())
    private val _userCardFi : MutableState<UserCard?> = mutableStateOf(UserCard())

    lateinit var userMessage: String

    fun cardValidationConsult() = viewModelScope.launch{

        // Mostramos el simbolo de carga
        _addUserCardFlow.value = Response.Loading
        loadingValues()
        val currentUserId = authUseCases.getCurrentUser()!!.uid

        val cardExist = async { userCardUseCases.verifyIfCardExistInDB(cardNumberInputUser.value, currentUserId) }.await()

        if (cardExist == true){
            userMessage = "La tarjeta ya la tienes agregada"
            showToast.value = true
            cancel()
        }

        // Lanzamos la primera tarea asincrona
        val validateCardTask = async { userCardUseCases.addUserCard.validateCard(cardNumberInputUser.value) }
        // esperamos que finalice su ejecución para obtener el resultado
        _userCardBi.value = validateCardTask.await()


        // Ejecutamos la lógica
        if (_userCardBi.value?.isValid == true){

            // Lanzamos la segunda tarea asincrona
            val getInfoCardTask = async { userCardUseCases.addUserCard.getFullInfoCard(cardNumberInputUser.value) }
            // esperamos que finalice su ejecución para obtener el resultado
            _userCardFi.value = getInfoCardTask.await()

            // ejecutamos la lógica de negocio
            if (_userCardFi.value?.cardNumber?.isDigitsOnly() == true){

                // Completamos la información de una tarjeta con la otra y le adicionamos el UserId que le asigna FirebaseAuth
                userCardUseCases.addUserCard.completeCardData(_userCardFi.value, _userCardBi.value, currentUserId)
                // Almacenamos la tarjeta en la BD Local
                userCardUseCases.saveCard(_userCardFi.value!!)

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
        showToast.value = true
        Log.e("MLOG", "Info(_userCardBi.value): ${_userCardBi.value}")
        Log.e("MLOG", "Info(cardNumerUserInput): ${cardNumberInputUser.value}")
        Log.i("MLOG", "Info(cardValidationConsult, addUserCardVM): ${_addUserCardFlow.value}")

    }


    // Validaciones de los datos

    fun enabledSaveButton() {
        isEnabledSaveButton.value =
            cardNumberInputUser.value.length == 16 && cardNumberInputUser.value.isDigitsOnly()
    }

    fun setFormatToCardNumber(cardNumber: String) {
        if(cardNumber.trim().length <= 16 && cardNumber.trim().isDigitsOnly()){
            cardNumberInputUser.value = cardNumber.trim()
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
        cardNumber.value = stringBuilder.toString()

    }

    fun loadingValues(){
        isEnabledSaveButton.value = false
        isEnabledTextInput.value = false
    }

    fun resetValues(){
        _addUserCardFlow.value = null
        isEnabledTextInput.value = true
    }

}