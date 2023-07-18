package com.ccastro.maas.domain.repository

interface LocationProviderRepository {

    interface LocationListener {
        fun onLocationObtained(latitude: Double, longitude: Double)
        fun onLocationError(error: String)
    }

    fun requestLocation(listener: LocationListener)
}
