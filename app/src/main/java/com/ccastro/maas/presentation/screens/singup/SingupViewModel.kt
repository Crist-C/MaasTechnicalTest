package com.ccastro.maas.presentation.screens.singup

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.User
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingupViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel(){

    var name: MutableState<String> = mutableStateOf("")
    var isNameValid: MutableState<Boolean> = mutableStateOf(false)
    var nameErrorMsg: MutableState<String> = mutableStateOf("")
    
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMsg: MutableState<String> = mutableStateOf("")
    
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMsg: MutableState<String> = mutableStateOf("")
    
    var passwordValidate: MutableState<String> = mutableStateOf("")
    var isPasswordValidateValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordValidateErrorMsg: MutableState<String> = mutableStateOf("")

    var isEnabledSingupButton = false


    fun onSignup(){
        val user = User(
            username = name.value,
            email = email.value,
            password = password.value,
            passwordConfirm = passwordValidate.value
        )

        singup(user)
    }

    // Corroutines

    private val _singupFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val singupFlow: StateFlow<Response<FirebaseUser>?> = _singupFlow
    fun singup(user: User) = viewModelScope.launch {
        // Valor inicial
        _singupFlow.value = Response.Loading
        val result = authUseCases.singUp(user)
        _singupFlow.value = result
    }


    //      Validaición de los campos del formulario
    fun enabledLoginButton() {
        isEnabledSingupButton = isNameValid.value && isEmailValid.value && isPasswordValid.value && isPasswordValidateValid.value
    }

    fun validateUserName() {
        val totalPalabras = name.value.trim().split("\\s+".toRegex()).size
        Log.d("SingupViewModel", "totalPalabras : $totalPalabras")
        if(name.value.trim().split("\\s+".toRegex()).size >= 2){
            isNameValid.value = true
            nameErrorMsg.value = ""
        }else{
            isNameValid.value = false
            nameErrorMsg.value = "Ingresa al menos un nombre y apellido"
        }
    }

    fun validateEmail() {
        if(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true
            emailErrorMsg.value = ""
        }else{
            isEmailValid.value = false
            emailErrorMsg.value = "email no valido"
        }

        enabledLoginButton()
    }

    fun validatePassword(){
        if(password.value.length >= 6){
            isPasswordValid.value = true
            passwordErrorMsg.value = ""
        } else{
            isPasswordValid.value = false
            passwordErrorMsg.value = "Ingresa al menos 6 caracteres"
        }

        enabledLoginButton()
    }

    fun validatePasswordConfirm(){
        if(passwordValidate.value == password.value){
            isPasswordValidateValid.value = true
            passwordValidateErrorMsg.value = ""
        } else{
            isPasswordValidateValid.value = false
            passwordValidateErrorMsg.value = "Las contraseñas no coinciden"
        }

        enabledLoginButton()
    }

}