package com.ccastro.maas.domain.use_cases.userCard

import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.repository.UserCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUserCards @Inject constructor(private val repository: UserCardRepository){

    operator fun invoke(currentUserId: String) : Flow<List<UserCard>> = repository.getAllUserCards(currentUserId)
}