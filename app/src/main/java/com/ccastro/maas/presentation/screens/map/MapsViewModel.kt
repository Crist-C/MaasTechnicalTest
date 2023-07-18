package com.ccastro.maas.presentation.screens.map


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ccastro.maas.domain.model.StoppingPlace
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.maps.android.compose.MarkerState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(MapsState())
        private set

    private val placesString = savedStateHandle.get<String>("nearPlaces")
    private val gson = Gson()
    val places : List<StoppingPlace> = gson.fromJson(
        placesString,
        object : TypeToken<List<StoppingPlace>>(){}.type
    )

    //val singapore = LatLng(1.35, 103.87)
    var singaporeState: MarkerState? = null

    init {
        state = state.copy(
            placesList = mutableListOf(places.iterator().next())
        )
        singaporeState = MarkerState(position = LatLng(state.placesList[0].lat, state.placesList[0].lon))
    }

}