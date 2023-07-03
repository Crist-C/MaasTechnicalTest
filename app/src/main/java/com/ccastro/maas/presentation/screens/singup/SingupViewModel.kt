package com.ccastro.maas.presentation.screens.singup

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingupViewModel @Inject constructor(): ViewModel(){

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