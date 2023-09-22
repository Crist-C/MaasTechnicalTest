package com.ccastro.maas.data.repository

import android.util.Log
import com.ccastro.maas.data.datasource.local.daos.UserCardDAO
import com.ccastro.maas.data.api.tullave.RestCardDataSource
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.repository.UserCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class UserCardRepositoryImpl @Inject constructor(
    @Named("RestDataSourceTullave") private val apiDataSource: RestCardDataSource,
    private val userCardDAO: UserCardDAO
) : UserCardRepository {

    override suspend fun existUserCardOnDB(cardNumber: String, currentUserId: String): Boolean? {
        return try {
            val id = userCardDAO.verifyIfCardExist(cardNumber, currentUserId)
            id > 0
        }catch (e: Exception){
            Log.e("MLOG","Error: existUserCardOnDB = "+e.stackTrace)
            null
        }
    } //

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
    override fun getAllUserCards(currentUserId: String): Flow<List<UserCard>> {
        return userCardDAO.getAll(currentUserId)
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