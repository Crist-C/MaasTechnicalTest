package com.ccastro.maas.domain.repository

import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun create(user: User): Response<Boolean>

    fun getUserById(id: String): Flow<User>
}