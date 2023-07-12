package com.ccastro.maas.domain.use_cases.auth

import com.ccastro.maas.domain.model.User
import com.ccastro.maas.domain.repository.AuthRepository
import javax.inject.Inject

class SingUp @Inject constructor(private val repository: AuthRepository){

    suspend operator fun invoke(user: User, password: String) = repository.singUp(user, password)
}