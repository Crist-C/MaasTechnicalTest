package com.ccastro.maas.data.repository

import com.ccastro.maas.data.datasource.RoomLocalDB
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.repository.UserCardRepository
import org.json.JSONObject
import javax.inject.Inject

class UserCardRepositoryImpl @Inject constructor(private val dataSource: RoomLocalDB<UserCard>) : UserCardRepository {

    override var userCard: UserCard? = UserCard()

    override var userCardList: MutableList<UserCard>? = null


    override suspend fun validate(cardNumber: String): Response<JSONObject> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCard(card: UserCard): Response<Boolean> {
        return try {
            dataSource.create(card)
            val result = dataSource.has(card)
            Response.Success(result)
        }catch (e: Exception){
            e.stackTrace
            Response.Fail(e)
        }
    }

    override suspend fun getCardById(id: Int): Response<UserCard?> {
        return try {
            val result = dataSource.readById(id)
            userCard = result
            Response.Success(result)
        }catch (e: Exception){
            e.stackTrace
            Response.Fail(e)
        }
    }

    override suspend fun getAllCards(): Response<MutableList<UserCard>?> {
        return try {
            val result = dataSource.getAll()
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