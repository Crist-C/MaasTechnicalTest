package com.ccastro.maas.domain.repository

import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    fun logout()

    suspend fun singUp(user: User): Response<FirebaseUser>
    // Con retrofit la respuesta sería un Json u otro tipo
    //fun login(email: String, password: String): Response<FirebaseUser>

}