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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingupViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val userUseCases: UserUseCases): ViewModel(){

    var name by mutableStateOf("")
    var isNameValid by mutableStateOf(false)
    var nameErrorMsg by mutableStateOf("")
    
    var email by mutableStateOf("")
    var isEmailValid by mutableStateOf(false)
    var emailErrorMsg by mutableStateOf("")
    
    var password by mutableStateOf("")
    var isPasswordValid by mutableStateOf(false)
    var passwordErrorMsg by mutableStateOf("")
    
    var passwordValidate by mutableStateOf("")
    var isPasswordValidateValid by mutableStateOf(false)
    var passwordValidateErrorMsg by mutableStateOf("")

    var isEnabledSingupButton = false

    private var _singupFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val singupFlow: StateFlow<Response<FirebaseUser>?> = _singupFlow

    var user = User()

    fun onClickSignup(){
        user = user.copy(
            username = name,
            email = email
        )
        singup(user)
    }

    fun singup(user: User) = viewModelScope.launch {
        // Valor inicial
        _singupFlow.value = Response.Loading
        val result = authUseCases.singUp(user, password)
        _singupFlow.value = result
    }

    fun createUser() = viewModelScope.launch{
        user.id = authUseCases.getCurrentUser()!!.uid
        userUseCases.create(user)
    }



    //      Validaición de los campos del formulario
    fun enabledLoginButton() {
        isEnabledSingupButton = isNameValid && isEmailValid && isPasswordValid && isPasswordValidateValid
    }

    fun validateUserName() {
        val totalPalabras = name.trim().split("\\s+".toRegex()).size
        Log.d("SingupViewModel", "totalPalabras : $totalPalabras")
        if(name.trim().split("\\s+".toRegex()).size >= 2){
            isNameValid = true
            nameErrorMsg = ""
        }else{
            isNameValid = false
            nameErrorMsg = "Ingresa al menos un nombre y apellido"
        }
    }

    fun validateEmail() {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            isEmailValid = true
            emailErrorMsg = ""
        }else{
            isEmailValid = false
            emailErrorMsg = "email no valido"
        }

        enabledLoginButton()
    }

    fun validatePassword(){
        if(password.length >= 6){
            isPasswordValid = true
            passwordErrorMsg = ""
        } else{
            isPasswordValid = false
            passwordErrorMsg = "Ingresa al menos 6 caracteres"
        }

        enabledLoginButton()
    }

    fun validatePasswordConfirm(){
        if(passwordValidate == password){
            isPasswordValidateValid = true
            passwordValidateErrorMsg = ""
        } else{
            isPasswordValidateValid = false
            passwordValidateErrorMsg = "Las contraseñas no coinciden"
        }

        enabledLoginButton()
    }

}