package com.ccastro.maas.presentation.screens.map.components


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ccastro.maas.presentation.screens.map.MapsViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapContent(viewModel: MapsViewModel = hiltViewModel()) {

    val state = viewModel.state

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(state.placesList[0].lat, state.placesList[0].lon), 17f)
    }
    Box(modifier = Modifier.fillMaxSize()){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){
            viewModel.places.forEach {
                place ->
                Log.i("MLOG", "placesList: $place")
                Marker(
                    state = MarkerState(LatLng(place.lat, place.lon)),
                    title = place.name
                )
            }
        }
        Text(text = "MY FIRST MAPA", style = MaterialTheme.typography.headlineMedium)
    }
}