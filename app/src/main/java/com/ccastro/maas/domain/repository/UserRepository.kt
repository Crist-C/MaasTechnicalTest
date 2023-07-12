package com.ccastro.maas.domain.repository

import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.User

interface UserRepository {

    suspend fun create(user: User): Response<Boolean>
}