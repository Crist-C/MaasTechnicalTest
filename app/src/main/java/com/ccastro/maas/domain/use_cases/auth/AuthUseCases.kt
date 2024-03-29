package com.ccastro.maas.domain.use_cases.auth

/**
 * Relaciona todos los casos de uso referentes a la autenticación
 * tales como:
 *  Login
 *  GetCurrentUser
 *  Logout
 */
data class AuthUseCases (

    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val logout: Logout,
    val singUp: SingUp

)