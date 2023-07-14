package com.ccastro.maas.presentation.screens.appPresentation.components

import com.google.firebase.auth.FirebaseUser

data class AppPresentationState(
    val nextRoute: String? = null,
    var currentUser: FirebaseUser? = null
)
