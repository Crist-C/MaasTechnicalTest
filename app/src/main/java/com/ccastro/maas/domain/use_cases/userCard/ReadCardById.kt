package com.ccastro.maas.domain.use_cases.userCard

import com.ccastro.maas.domain.repository.UserCardRepository
import javax.inject.Inject

class ReadCardById @Inject constructor(private val repository: UserCardRepository) {
    suspend operator fun invoke(id: Int) = repository.getCardById(id)

}