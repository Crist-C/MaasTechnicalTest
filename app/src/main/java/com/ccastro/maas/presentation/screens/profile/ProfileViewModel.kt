package com.ccastro.maas.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val userUseCases: UserUseCases) : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    init {
        state.currentUser = authUseCases.getCurrentUser()?.uid
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch{
        userUseCases.getUserById(state.currentUser!!).collect(){
            state = state.copy(
                userData = it
            )
        }
    }

    fun logout(){
        authUseCases.logout()
    }
}