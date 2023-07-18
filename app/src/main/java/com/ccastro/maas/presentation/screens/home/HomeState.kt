package com.ccastro.maas.presentation.screens.home

import android.app.Activity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ccastro.maas.domain.model.DialogContents
import com.ccastro.maas.domain.model.StoppingPlace
import com.ccastro.maas.domain.model.UserCard
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.gson.Gson


data class HomeState(

    val TAG: String = "MLOG",

    var activity: Activity? = null,

    val CODIGO_EN_SEGUNDO_PLANO: Int = 100,
    var isPermission: Boolean = false,
    var fusedLocationClient: FusedLocationProviderClient? = null,
    var locationCallback: LocationCallback? = null,
    var locationRequest: LocationRequest? = null,
    var timeRequest: Long = 30000,

    var userCards: List<UserCard> = emptyList(),
    var stopPlaces: MutableState<List<StoppingPlace>> = mutableStateOf(emptyList()),

    val gson: Gson = Gson(),

    var habilitarActualizarRutas: Boolean = true,

    var showDialog: Boolean = false,

    var dialogContent: DialogContents? = null,

    var currentCardUser: UserCard = UserCard(),

    var lat: Double = 4.722557,
    var lon: Double = -74.131103,
    var rad: Int = 500,


    )
