package com.ccastro.maas.domain.use_cases.stopPlaces

import com.ccastro.maas.domain.repository.TripPlanerRepository
import javax.inject.Inject

class GetNearStopPlaces @Inject constructor(private val repository: TripPlanerRepository) {

    suspend operator fun invoke(latitude: Double, longitud: Double, radius: Int) = repository.getNearStoppinPlaces(latitude, longitud, radius)

}
