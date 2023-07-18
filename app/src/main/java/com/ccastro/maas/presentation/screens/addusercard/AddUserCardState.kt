package com.ccastro.maas.presentation.screens.addusercard

import com.ccastro.maas.core.Constans.DEMO_CARD_NUMBER
import com.ccastro.maas.domain.model.UserCard

data class AddUserCardState(

    var userCard: UserCard = UserCard(),

    var isEnabledSaveButton: Boolean = false,
    var isEnabledTextInput: Boolean = true,
    
    var cardNumberOnImage: String = "0000 0000 0000 0000",
    var cardNumberInputUser: String = DEMO_CARD_NUMBER,
    
    var showToast: Boolean = false,

    var userCardBi: UserCard = UserCard(),  // Basic Information
    var userCardFi: UserCard = UserCard(),  // Full Information
    
    var userMessage: String = "",

)
