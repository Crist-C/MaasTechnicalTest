package com.ccastro.maas.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermisionScreen(
    permissions: List<String>,
    onIsGranted: ()->Unit,
    onIsDenied: ()->Unit,
    onIsShowRationale: ()->Unit
){
    val permissionState = rememberMultiplePermissionsState( permissions = permissions )
    var showAlertDialog = false

    if(permissionState.allPermissionsGranted){
        LaunchedEffect(key1 = true){
            onIsGranted()
        }
    }else if (permissionState.shouldShowRationale){
        showAlertDialog = true
        DefaultAlertDialog(
            title = "Acceso a tu ubicación",
            textDialog = "No almacenamos tu ubicación, solo la usamos para consultar tus paraderos cercanos y ubicarte en el mapa",
            onConfirm = { permissionState.launchMultiplePermissionRequest() },
            onDismiss = { showAlertDialog = false},
            showDialog = showAlertDialog
        )
    }else{
        DefaultAlertDialog(
            title = "Funcionalidades limitadas",
            textDialog = "Las funcionalidades cómo tus paraderos cercanos no estáran disponibles.",
            onConfirm = { permissionState.launchMultiplePermissionRequest() },
            onDismiss = { showAlertDialog = false},
            showDialog = showAlertDialog
        )
    }

    LaunchedEffect(key1 = true){
        permissionState.launchMultiplePermissionRequest()
    }

}