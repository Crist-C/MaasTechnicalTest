package com.ccastro.maas.data.repository

import android.util.Log
import com.ccastro.maas.data.datasource.RestTripDataSource
import com.ccastro.maas.domain.model.StoppingPlace
import com.ccastro.maas.domain.repository.TripPlanerRepository
import javax.inject.Inject
import javax.inject.Named

class TripPlanerRepositoryImp @Inject constructor(@Named("restDataSourceTrip") private val apiDataSource: RestTripDataSource) : TripPlanerRepository {


    override suspend fun getNearStoppinPlaces(latitud: Double, longitud: Double, radius: Int): List<StoppingPlace> {
        return try {
            val response = apiDataSource.getStops(latitud, longitud, radius)
            Log.i("MLOG", "Info(getNeaStoppinPlaces): ResponseAPI: $response")
            response
            //Response.Success(response)
        }catch (e: Exception){
            Log.e("MLOG", "getNearStoppinPlaces: Exception: ${e.message}")
            //Response.Fail(e)
            listOf(StoppingPlace(id = "123456", name = "Fail TestStop", dist = "100"))
        }

    }

}