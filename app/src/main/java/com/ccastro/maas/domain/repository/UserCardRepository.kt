package com.ccastro.maas.domain.repository

import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.UserCard
import kotlinx.coroutines.flow.Flow

interface UserCardRepository {

    val userCard: UserCard?
    val userCardList: MutableList<UserCard>?

    suspend fun validate(cardNumber: String): Response<UserCard>

    suspend fun getFullCardInfoRequest(cardNumber: String): Response<UserCard>

    fun getAllUserCards(currentUserId: String): Flow<List<UserCard>>

    suspend fun saveCard(card: UserCard): Response<Int>

    suspend fun deleteCard(userCard: UserCard)

    suspend fun getTotalUserCards() : Int

}