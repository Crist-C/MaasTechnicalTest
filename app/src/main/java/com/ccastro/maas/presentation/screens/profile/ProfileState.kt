package com.ccastro.maas.presentation.screens.profile

import com.ccastro.maas.domain.model.User

data class ProfileState(

    var userData: User = User(),
    var currentUser: String? = null

)
