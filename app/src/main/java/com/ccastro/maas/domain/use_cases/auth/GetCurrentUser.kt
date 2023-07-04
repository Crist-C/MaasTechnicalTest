package com.ccastro.maas.domain.use_cases.auth

import com.ccastro.maas.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.currentUser
}