package com.ccastro.maas.domain.use_cases.userCard

import android.util.Log
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.repository.UserCardRepository
import javax.inject.Inject

class AddUserCard @Inject constructor (private val repository: UserCardRepository) {

    fun getUserFromResponse(response: Response<UserCard>): UserCard? {
        when(response){
            is Response.Fail -> ""
            Response.Loading -> ""
            is Response.Success -> return response.data
        }
        return null
    }

    fun completeCardData(cardTwo: UserCard?, cardOne: UserCard?, currentUserId: String) : UserCard? {
        cardTwo?.card = cardOne?.card
        cardTwo?.isValid = cardOne?.isValid
        cardTwo?.status = cardOne?.status
        cardTwo?.statusCode = cardOne?.statusCode
        cardTwo?.currentUserId = currentUserId
        return cardTwo
    }

    suspend fun validateCard(cardNumber: String): UserCard? {
        when(val userCardResponse = repository.validate(cardNumber)){
            is Response.Fail -> {
                UserCard(isValid = false)
                Log.e("MLOG", "userCardResponse(validateCard, Response<UserCard>): $userCardResponse")
            }
            Response.Loading -> null
            is Response.Success -> return userCardResponse.data
        }
        return UserCard(isValid = false)
    }

    suspend fun getFullInfoCard(cardNumber: String): UserCard {
        try {
            val response = repository.getFullCardInfoRequest(cardNumber)
            if (response is Response.Success){
                Log.i("MLOG", "response(getFullInfoCard,Response<UserCard>): $response")
                return getUserFromResponse(response)!!
            }
        }catch (e : Exception){
            Log.i("MLOG", "defered(getFullInfoCard,Response<UserCard>): ${e.message}")
        }
        return UserCard()
    }
}