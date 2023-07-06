package com.ccastro.maas.domain.repository

import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.StoppingPlace

interface TripPlanerRepository {

    suspend fun getNeaStoppinPlaces(latitud: Double, longitud: Double, radius : Int) : Response<List<StoppingPlace>>
}