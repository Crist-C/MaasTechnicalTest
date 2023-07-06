package com.ccastro.maas.domain.repository

import com.ccastro.maas.domain.model.StoppingPlace

interface TripPlanerRepository {

    suspend fun getNearStoppinPlaces(latitud: Double, longitud: Double, radius : Int) : List<StoppingPlace>
}