package com.ccastro.maas.domain.use_cases.userCard

import com.ccastro.maas.domain.repository.UserCardRepository
import javax.inject.Inject

class VerifyIfCardExistInDB @Inject constructor(private val repository: UserCardRepository) {
    suspend operator fun invoke(cardNumber: String, currentUserId: String) = repository.existUserCardOnDB(cardNumber, currentUserId)
}