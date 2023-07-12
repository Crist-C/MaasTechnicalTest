package com.ccastro.maas.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ccastro.maas.domain.model.StoppingPlace
import com.ccastro.maas.domain.model.UserCard


data class HomeState(

    var userCards: MutableState<List<UserCard>> = mutableStateOf(emptyList()),
    var stopPlaces: MutableState<List<StoppingPlace>> = mutableStateOf(emptyList()),

    var showDialog: MutableState<Boolean>  = mutableStateOf(false),

    var titleDialog: String = "Eliminar tarjeta",
    var textDialog: String = "Desea eliminar la tarjeta?\n",

    var confirmFunction: String = "",
    var currentCardUser: UserCard = UserCard(),

    var lat: Double = 4.722557,
    var lon: Double = -74.131103,
    var rad: Int = 150,


    )
