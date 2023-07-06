package com.ccastro.maas.data.datasource

import com.ccastro.maas.domain.model.StoppingPlace
import com.ccastro.maas.domain.model.UserCard
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface RestTripDataSource {

    @GET("?lat={lat}&lon={lon}&radius={radius}") // 1010000008582546
    suspend fun getStops(@Path("lat") lat: Double, @Path("lon") lon: Double, @Path("radius") radius: Int): List<StoppingPlace>

}