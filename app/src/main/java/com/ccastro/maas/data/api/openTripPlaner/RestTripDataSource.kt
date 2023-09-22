package com.ccastro.maas.data.api.openTripPlaner

import com.ccastro.maas.domain.model.StoppingPlace
import retrofit2.http.GET
import retrofit2.http.Query

interface RestTripDataSource {
    /**
     * Consumo de servicios REST a la API de TripPlaner
     */

    @GET("/otp/routers/default/index/stops")
    suspend fun getStops(@Query("lat") latitud: Double, @Query("lon") longitud: Double, @Query("radius") range: Int): List<StoppingPlace>

}