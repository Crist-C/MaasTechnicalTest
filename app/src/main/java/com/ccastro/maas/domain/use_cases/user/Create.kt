package com.ccastro.maas.domain.use_cases.user

import com.ccastro.maas.domain.model.User
import com.ccastro.maas.domain.repository.UserRepository
import javax.inject.Inject

class Create @Inject constructor(private val userRepository: UserRepository){

    suspend operator fun invoke(user: User) = userRepository.create(user)
}