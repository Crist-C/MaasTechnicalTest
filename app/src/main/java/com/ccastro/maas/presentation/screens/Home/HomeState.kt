package com.ccastro.maas.presentation.screens.Home

import com.ccastro.maas.domain.model.StoppingPlace
import com.ccastro.maas.domain.model.UserCard


data class HomeState(

    var userCards: List<UserCard> = emptyList(),
    var stopPlaces: List<StoppingPlace> = emptyList(),

    )
