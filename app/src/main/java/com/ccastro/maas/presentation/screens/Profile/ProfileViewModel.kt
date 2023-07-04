package com.ccastro.maas.presentation.screens.Profile

import androidx.lifecycle.ViewModel
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    fun logout(){
        authUseCases.logout()
    }
}