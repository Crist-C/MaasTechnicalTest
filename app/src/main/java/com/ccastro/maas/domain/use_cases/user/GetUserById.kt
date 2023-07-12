package com.ccastro.maas.domain.use_cases.user

import com.ccastro.maas.domain.repository.UserRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(id: String) = repository.getUserById(id)
}