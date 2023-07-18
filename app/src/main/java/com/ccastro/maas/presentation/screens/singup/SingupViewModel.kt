package com.ccastro.maas.presentation.screens.singup

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.User
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.domain.use_cases.user.UserUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingupViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val userUseCases: UserUseCases): ViewModel(){

    var state by mutableStateOf(SingupState())
        private set


    // SingUp Response
    var singupResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    fun onClickSignup(){
        state.user = state.user.copy(
            username = state.name,
            email = state.email
        )
        singup(state.user)
    }

    fun singup(user: User) = viewModelScope.launch {
        // Valor inicial
        singupResponse = Response.Loading
        val result = authUseCases.singUp(user, state.password)
        singupResponse = result
    }

    fun createUser() = viewModelScope.launch{
        state.user.id = authUseCases.getCurrentUser()!!.uid
        userUseCases.create(state.user)
    }

    // Input User states
    fun onNameChange(name: String){
        state = state.copy(name = name)
    }

    fun onEmailChange(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordChange(password: String){
        state = state.copy(password = password)
    }

    fun onPasswordValidateChange(paswordValidate: String){
        state = state.copy(passwordValidate = paswordValidate)
    }

    //  Validaición de los campos del formulario
    private fun enabledLoginButton() {
        state.isEnabledSingupButton = state.isNameValid && state.isEmailValid && state.isPasswordValid && state.isPasswordValidateValid
    }

    fun validateUserName() {
        val totalPalabras = state.name.trim().split("\\s+".toRegex()).size
        Log.d("SingupViewModel", "totalPalabras : $totalPalabras")
        if(state.name.trim().split("\\s+".toRegex()).size >= 2){
            state.isNameValid = true
            state.nameErrorMsg = ""
        }else{
            state.isNameValid = false
            state.nameErrorMsg = "Ingresa al menos un nombre y apellido"
        }
    }

    fun validateEmail() {
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            state.isEmailValid = true
            state.emailErrorMsg = ""
        }else{
            state.isEmailValid = false
            state.emailErrorMsg = "state.email no valido"
        }
        enabledLoginButton()
    }

    fun validatePassword(){
        if(state.password.length >= 6){
            state.isPasswordValid = true
            state.passwordErrorMsg = ""
        } else{
            state.isPasswordValid = false
            state.passwordErrorMsg = "Ingresa al menos 6 caracteres"
        }
        enabledLoginButton()
    }

    fun validatePasswordConfirm(){
        if(state.passwordValidate == state.password){
            state.isPasswordValidateValid = true
            state.passwordValidateErrorMsg = ""
        } else{
            state.isPasswordValidateValid = false
            state.passwordValidateErrorMsg = "Las contraseñas no coinciden"
        }
        enabledLoginButton()
    }

}