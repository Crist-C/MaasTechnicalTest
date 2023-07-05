package com.ccastro.maas.presentation.screens.AddUserCard

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.model.ValidationCardResponse
import com.ccastro.maas.domain.use_cases.userCard.UserCardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserCardViewModel @Inject constructor(private val userCardUseCases: UserCardUseCases) : ViewModel() {

    var userCard: UserCard = UserCard()

    var isEnabledSaveButton: MutableState<Boolean> = mutableStateOf(false)

    var cardNumber: MutableState<String> = mutableStateOf("0000 0000 0000 0000")
    var cardNumberInputUser: MutableState<String> = mutableStateOf("")

    private val _saveCardFlow = MutableStateFlow<Response<Boolean>?>(value = null)
    val saveCardFlow: StateFlow<Response<Boolean>?> = _saveCardFlow

    private val _validateCardFlow = MutableStateFlow<Response<ValidationCardResponse>?>(value = null)
    val validateCardFlow: StateFlow<Response<ValidationCardResponse>?> = _validateCardFlow

    fun cardValidationConsult() = viewModelScope.launch{
        _validateCardFlow.value = Response.Loading
        val result = userCardUseCases.validateCard(cardNumberInputUser.value)
        _validateCardFlow.value = result
        Log.i("validateCardFlowModel", _validateCardFlow.value.toString())
    }

    fun saveCard(userCard: UserCard) = viewModelScope.launch{
        _saveCardFlow.value = Response.Loading
        val result = userCardUseCases.saveCard(userCard)
        _saveCardFlow.value = result
    }

    fun onSaveCard(){
        val userCard = UserCard(
            cardNumber = cardNumberInputUser.value,
            userName = "Cristian",
            userLastName = "Castro"
        )
        saveCard(userCard)
    }

    // Validate functions

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

}