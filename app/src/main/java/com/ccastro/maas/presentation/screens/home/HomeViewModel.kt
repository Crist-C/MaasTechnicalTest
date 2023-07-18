package com.ccastro.maas.presentation.screens.home



import android.app.Activity
import android.location.Location
import android.os.Build
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.DialogContents
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.domain.use_cases.stopPlaces.StopPlacesUseCases
import com.ccastro.maas.domain.use_cases.userCard.UserCardUseCases
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

// Al ViewModel se le injectan la clase que contiene los casos de uso

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userCardUseCases: UserCardUseCases,
    private val stopPlacesUseCases: StopPlacesUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set


/*
    fun verificarPermisos(){
        val permisos = arrayListOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            permisos.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        val permisosTypedArray = permisos.toTypedArray()
        if(tienePermisos(permisosTypedArray)){
            isPermission = true
            onPermisosConcedidos()
        }else{
            solicitarPermisos(permisosTypedArray)
        }
    }

    private fun solicitarPermisos(permisos: Array<String>) {
        state.activity?.let {
            ActivityCompat.requestPermissions(
                it,
                permisos,
                state.CODIGO_EN_SEGUNDO_PLANO
            )
        }

    }

    private fun tienePermisos(permisos: Array<String>): Boolean{
        if (state.activity != null){
            return permisos.all {
                return ContextCompat.checkSelfPermission( state.activity!!, it) == PackageManager.PERMISSION_GRANTED
            }
        }
        else{
            Log.i(state.TAG, "tienePermisos: Context is null")
        }
        return true
    }
*/

    fun onPermisosConcedidos(){
        state.fusedLocationClient = LocationServices.getFusedLocationProviderClient(state.activity!!)
        try {
            state.fusedLocationClient!!.lastLocation.addOnSuccessListener {
                if(it != null){
                    //setLatitudeAndLongitude(it)
                }else{
                    Toast.makeText(state.activity, "No se logró obtener su ubicación actual", Toast.LENGTH_SHORT).show()
                }
            }

            state.locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                state.timeRequest
            ).apply {
                setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                setWaitForAccurateLocation(true)
            }.build()

            state.locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations){
                        setLatitudeAndLongitude(location)
                    }
                }
            }

            state.fusedLocationClient!!.requestLocationUpdates(
                state.locationRequest!!,
                state.locationCallback!!,
                Looper.getMainLooper()
            )

        }catch (_:SecurityException){
            
        }

    }

    fun setLatitudeAndLongitude(location: Location){
        state.lat = location.latitude
        state.lon = location.longitude
        Log.i("MLOG", "setLatitudeAndLongitude: Lat = ${state.lat} Lon = ${state.lon}")
        if(state.habilitarActualizarRutas){
            actualizarRutas()
        }
    }

    fun setActivity(activity: Activity){
        state = state.copy(
            activity = activity
        )
    }

    fun stopLocationRequest(){
        state = state.copy(
            habilitarActualizarRutas = false,
            timeRequest = 5000
        )
    }

    init {
        viewModelScope.launch {
            userCardUseCases.getAllUserCards(authUseCases.getCurrentUser()!!.uid).collectLatest {
                state = state.copy(userCards = it)
            }
        }
        state = state.copy(
            habilitarActualizarRutas = true,
            timeRequest = 30000
        )
    }

    @RequiresApi(Build.VERSION_CODES.P)

    fun getUserLocation() {
        /*TODO: Pendiente implementar en el caso de uso*/
    }

    private fun actualizarRutas() {
        viewModelScope.launch {
            try {
                runBlocking {
                    val task = async {
                        stopPlacesUseCases.getNearStopPlaces(
                            latitude = state.lat,
                            longitud = state.lon,
                            radius = state.rad
                        )
                    }
                    val stopList = task.await()
                    state.stopPlaces.value = stopList
                }
            } catch (e: Exception) {
                Log.e("MLOG", "ExceptionRadList ${e.message}")
            }
        }
    }

    fun validateDialogContent(userCard: UserCard) {
        if (userCard == UserCard()) {
            state.dialogContent = DialogContents.Agregar
            (state.dialogContent as DialogContents.Agregar).onCallFunction = {}
        } else {
            state.currentCardUser = userCard
            state.dialogContent = DialogContents.Eliminar
            (state.dialogContent as DialogContents.Eliminar).onCallFunction =
                { deleteUserCardOnDB() }
        }
        openDialog()
    }

    private fun openDialog() {
        state = state.copy(showDialog = true)
    }

    fun onDialogConfirm() {
        state.dialogContent = null
        state = state.copy(showDialog = false)
    }

    fun onDialogDismiss() {
        state.dialogContent = null
        state = state.copy(showDialog = false)
    }

    private fun deleteUserCardOnDB() {
        viewModelScope.launch(Dispatchers.IO) {
            userCardUseCases.deleteCard(state.currentCardUser)
            state.currentCardUser = UserCard()
        }
    }

}
