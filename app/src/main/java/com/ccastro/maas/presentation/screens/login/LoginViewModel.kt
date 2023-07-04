package com.ccastro.maas.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMsg: MutableState<String> = mutableStateOf("")

    var isEnabledLoginButton: MutableState<Boolean> = mutableStateOf(false)

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(value = null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow

    val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null){   // SESIÓN INICIADA - INGRESA DIRECTAMENTE PORQUE YA EXISTE UN USUARIO LOGUEADO
            _loginFlow.value = Response.Success(currentUser)
        }
    }

    //      Login
    fun login() = viewModelScope.launch {
        _loginFlow.value = Response.Loading
        val result = authUseCases.login(email = email.value, password = password.value)
        _loginFlow.value = result

    }


    //      Validaciones del formulario

    fun enabledLoginButton() {
        isEnabledLoginButton.value = isEmailValid.value && isPasswordValid.value
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
            passwordErrorMsg.value = "al menos 6 caracteres"
        }

        enabledLoginButton()
    }

}