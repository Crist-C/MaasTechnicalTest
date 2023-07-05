package com.ccastro.maas.domain.repository

import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.UserCard

interface UserCardRepository {

    val userCard: UserCard?
    val userCardList: MutableList<UserCard>?

    suspend fun validate(cardNumber: String): Response<UserCard>

    suspend fun getFullCardInfoRequest(cardNumber: String): Response<UserCard>

    suspend fun saveCard(card: UserCard): Response<Boolean>

    suspend fun getCardById(id: Int): Response<UserCard?>

    suspend fun getCardByNumber(cardNumber: String): Response<UserCard?>

    suspend fun getCardByField(fieldName: String, fieldValue: Any): Response<UserCard?>

    suspend fun getAllCards(): Response<MutableList<UserCard>?>

}