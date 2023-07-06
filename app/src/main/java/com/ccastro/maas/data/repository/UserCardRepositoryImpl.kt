package com.ccastro.maas.data.repository

import android.util.Log
import com.ccastro.maas.data.Mapper.UserCardDAO
import com.ccastro.maas.data.datasource.RestDataSource
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.repository.UserCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserCardRepositoryImpl @Inject constructor(
    private val apiDataSource: RestDataSource,
    private val userCardDAO: UserCardDAO) : UserCardRepository { //

    override var userCard: UserCard? = UserCard(id = 0)
    override var userCardList: MutableList<UserCard>? = null

    // Consumos del API
    override suspend fun validate(cardNumber: String): Response<UserCard> {
        return try {
            val response = apiDataSource.getValidationCardRequest(cardNumber)
            Log.i("validateCardFlowModelImpl", "validate: Response: $response")
            Response.Success(response)
        }catch (e: Exception){
            Log.e("validateCardFlowImpl", "validate: Exception: ${e.message}")
            Response.Fail(e)
        }
    }

    override suspend fun getFullCardInfoRequest(cardNumber: String): Response<UserCard> {
        return try {
            val response = apiDataSource.getUserCardInfoRequest(cardNumber)
            Log.i("validateCardFlowImpl", "getFullCardInfoRequest: Response: $response")
            Response.Success(response)
        }catch (e: Exception){
            Log.e("validateCardFlowImpl", "getFullCardInfoRequest: Exception: ${e.message}")
            Response.Fail(e)
        }
    }

    // Almacenamiento en la DB
    override fun getAllCards(): Flow<List<UserCard>> {
        return userCardDAO.getAll()
    }

    override suspend fun saveCard(card: UserCard): Response<Int> {
        return try {
            userCardDAO.insert(card)
            Response.Success(1)
        }catch (e: Exception){
            e.stackTrace
            Response.Fail(e)
        }
    }

    override suspend fun deleteCard(userCard: UserCard) {
        userCardDAO.delete(userCard)
    }

    override suspend fun getTotalUserCards(): Int {
        return userCardDAO.getTotalUserCards()
    }

}