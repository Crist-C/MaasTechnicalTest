package com.ccastro.maas.domain.use_cases.userCard

import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.repository.UserCardRepository
import javax.inject.Inject

class SaveCard @Inject constructor(private val repository: UserCardRepository){

    suspend operator fun invoke(userCard: UserCard) = repository.saveCard(userCard)
}