package com.ccastro.maas.data.datasource

import com.ccastro.maas.domain.model.UserCard
import retrofit2.http.GET
import retrofit2.http.Path

interface RestDataSource {


    @GET("/card/valid/{cardNumber}") // 1010000008582546
    suspend fun getValidationCardRequest(@Path("cardNumber") cardNumber: String ): UserCard

    @GET("/card/getInformation/1010000008582546") // 1010000008582546
    suspend fun getUserCardInfoRequest(): UserCard

}