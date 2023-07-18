package com.ccastro.maas.presentation.screens.appPresentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.presentation.navigation.Graph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppPresentationViewModel @Inject constructor(authUseCases: AuthUseCases): ViewModel() {

    var state by mutableStateOf(AppPresentationState())

    init {
        state.currentUser = authUseCases.getCurrentUser()
        state = if (state.currentUser != null){
            state.copy(nextRoute = Graph.HOME)
        }else {
            state.copy( nextRoute = Graph.AUTHENTICATION)
        }
    }

}