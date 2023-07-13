package com.ccastro.maas.presentation.screens.login

import com.google.firebase.auth.FirebaseUser

data class LoginState(

    var email: String = "",
    var isEmailValid: Boolean = false,
    var emailErrorMsg: String = "",
    
    var password: String = "",
    var isPasswordValid: Boolean = false,
    var passwordErrorMsg: String = "",
    
    var isEnabledLoginButton: Boolean = false,

    var currentUser: FirebaseUser? = null

)
