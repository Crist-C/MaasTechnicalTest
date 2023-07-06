package com.ccastro.maas.data.repository

import android.util.Log
import com.ccastro.maas.data.datasource.RestTripDataSource
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.StoppingPlace
import com.ccastro.maas.domain.repository.TripPlanerRepository
import javax.inject.Inject

class TripPlanerRepositoryImp @Inject constructor(private val apiDataSource: RestTripDataSource) : TripPlanerRepository {


    override suspend fun getNeaStoppinPlaces(latitud: Double, longitud: Double, radius: Int): Response<List<StoppingPlace>> {
        return try {
            val response = apiDataSource.getStops(latitud, longitud, radius)
            Log.i("MLOG", "Info(getNeaStoppinPlaces): ResponseAPI: $response")
            Response.Success(response)
        }catch (e: Exception){
            Log.e("validateCardFlowImpl", "getFullCardInfoRequest: Exception: ${e.message}")
            Response.Fail(e)
        }

    }

}