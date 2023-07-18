package com.ccastro.maas.domain.use_cases.userDevice

data class UserDeviceUsesCases(
    val requestLocationPermission: RequestLocationPermission,
    val getUserLocation: GetUserLocation
)