package com.ccastro.maas.presentation.screens.map

import com.ccastro.maas.domain.model.StoppingPlace

data class MapsState(

    var placesList: List<StoppingPlace> = emptyList(),
    val test: String = ""
)
