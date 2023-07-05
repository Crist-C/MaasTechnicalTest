package com.ccastro.maas.data.repository

import android.util.Log
import com.ccastro.maas.data.datasource.RestDataSource
import com.ccastro.maas.data.datasource.RoomLocalDB
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.model.ValidationCardResponse
import com.ccastro.maas.domain.repository.UserCardRepository
import javax.inject.Inject

class UserCardRepositoryImpl @Inject constructor(
    private val apiDataSource: RestDataSource,
    private val localDB: RoomLocalDB<UserCard>) : UserCardRepository { //

    override var userCard: UserCard? = UserCard()
    override var userCardList: MutableList<UserCard>? = null


    override suspend fun validate(cardNumber: String): Response<ValidationCardResponse> {
        return try {
            val response = apiDataSource.getValidationCard(cardNumber)
            Log.i("validateCardFlowModelImpl", "Response: $response")
            Response.Success(response)
        }catch (e: Exception){
            Log.e("validateCardFlowImpl", "Exception: $e.stackTrace")
            Response.Fail(e)
        }
    }

    override suspend fun saveCard(card: UserCard): Response<Boolean> {
        return try {
            localDB.create(card)
            val result = localDB.has(card)
            Response.Success(result)
        }catch (e: Exception){
            e.stackTrace
            Response.Fail(e)
        }
    }

    override suspend fun getCardById(id: Int): Response<UserCard?> {
        return try {
            val result = localDB.readById(id)
            userCard = result
            Response.Success(result)
        }catch (e: Exception){
            e.stackTrace
            Response.Fail(e)
        }
    }

    override suspend fun getAllCards(): Response<MutableList<UserCard>?> {
        return try {
            val result = localDB.getAll()
            userCardList = result
            Response.Success(result)
        }catch (e: Exception){
            e.stackTrace
            Response.Fail(e)
        }
    }

    override suspend fun getCardByNumber(cardNumber: String): Response<UserCard?> {
        TODO("Not yet implemented")
    }

    override suspend fun getCardByField(fieldName: String, fieldValue: Any): Response<UserCard?> {
        TODO("Not yet implemented")
    }
}