package com.ccastro.maas.domain.use_cases.userCard


import com.ccastro.maas.domain.repository.UserCardRepository
import javax.inject.Inject

class TotalUserCards @Inject constructor(private var repository: UserCardRepository){

    suspend operator fun invoke() = repository.getTotalUserCards()
}