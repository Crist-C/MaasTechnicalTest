package com.ccastro.maas.presentation.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccastro.maas.presentation.components.LocationPermisionScreen
import com.ccastro.maas.presentation.screens.home.components.HomeScreenContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    activity: Activity,
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.setActivity(activity)
    Scaffold(
        topBar = {
        },
        content = {
            HomeScreenContent(navHostController)
        },
        bottomBar = {}
    )
    LocationPermisionScreen(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ),
        onIsGranted = {
            if (viewModel.state.activity != null && viewModel.state.habilitarActualizarRutas ){
                viewModel.onPermisosConcedidos()
            }else{
                Toast.makeText(activity.applicationContext, "Context is null", Toast.LENGTH_SHORT).show()
            }
        },
        onIsDenied = { /*TODO*/ }) {
    }
}
