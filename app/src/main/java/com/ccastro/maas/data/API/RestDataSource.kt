package com.ccastro.maas.data.API

import com.ccastro.maas.domain.model.UserCard
import retrofit2.http.GET
import retrofit2.http.Path

interface RestDataSource {


    @GET("/card/valid/{cardNumber}") // 1010000008582546
    suspend fun getValidationCardRequest(@Path("cardNumber") cardNumber: String ): UserCard

    @GET("/card/getInformation/{cardNumber}") // 1010000008582546
    suspend fun getUserCardInfoRequest(@Path("cardNumber") cardNumber: String): UserCard

}