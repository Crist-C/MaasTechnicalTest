package com.ccastro.maas.data.datasource

import com.ccastro.maas.domain.model.ApiResponse
import com.ccastro.maas.domain.model.ValidationCardResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RestDataSource {


    @GET("/card/valid/{cardNumber}") // 1010000008582546
    suspend fun getValidationCard(@Path("cardNumber") cardNumber: String ): ValidationCardResponse

    @GET("/card/getInformation/{cardNumber}") // 1010000008582546
    suspend fun getCardInfo(): ApiResponse

}