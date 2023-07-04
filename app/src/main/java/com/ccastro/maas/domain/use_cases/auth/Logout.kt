package com.ccastro.maas.domain.use_cases.auth

import com.ccastro.maas.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRepository){

    operator fun invoke() = repository.logout()
}
