package com.ccastro.maas.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.maas.domain.model.User
import com.ccastro.maas.domain.use_cases.auth.AuthUseCases
import com.ccastro.maas.domain.use_cases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val userUseCases: UserUseCases) : ViewModel() {

    var userData by mutableStateOf(User())
        private set

    private val currentUser = authUseCases.getCurrentUser()?.uid

    init {
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch{
        userUseCases.getUserById(currentUser!!).collect(){
            userData = it
        }
    }

    fun logout(){
        authUseCases.logout()
    }
}