package com.ccastro.maas.domain.model

import com.ccastro.maas.R


data class UserCard(
    val cardNumber: String = "---- ---- ---- ----",
    val profileCode: String = "",
    val profile: String = "------",
    val profile_es: String = "------",
    val bankCode: String = "--",
    val bankName: String = "---------------",
    val userName: String = "---------",
    val userLastName: String = "---------",

    var card: String? = "",
    var isValid: Boolean? = false,
    var status: String? = "",
    var statusCode: Int? = 0,

    //TODO: validar si se debe crear otra clase
    val amount: Int = 0,
    val cardStatus: String = "----",
    val imageId: Int = if (cardStatus == "activa") {
        R.drawable.tullave_card_3
    } else {
        R.drawable.tullave_card_dont_exist
    }

    )