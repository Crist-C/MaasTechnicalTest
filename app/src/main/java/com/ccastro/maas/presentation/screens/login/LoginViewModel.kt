package com.ccastro.maas.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var email by mutableStateOf("")
    var isEmailValid by mutableStateOf(false)
    var emailErrorMsg by mutableStateOf("")

    var password by mutableStateOf("")
    var isPasswordValid by mutableStateOf(false)
    var passwordErrorMsg by mutableStateOf("")

    var isEnabledLoginButton by mutableStateOf(false)

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(value = null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow

    private val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null){   // SESIÓN INICIADA - INGRESA DIRECTAMENTE PORQUE YA EXISTE UN USUARIO LOGUEADO
            _loginFlow.value = Response.Success(currentUser)
        }
    }

    //      Login
    fun login() = viewModelScope.launch {
        _loginFlow.value = Response.Loading
        val result = authUseCases.login(email = email, password = password)
        _loginFlow.value = result

    }


    //      Validaciones del formulario

    fun enabledLoginButton() {
        isEnabledLoginButton = isEmailValid && isPasswordValid
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
            passwordErrorMsg = "al menos 6 caracteres"
        }

        enabledLoginButton()
    }

}