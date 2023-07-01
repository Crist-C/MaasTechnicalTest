package com.ccastro.maas


data class UserCard(
    val userName: String = "---------",
    val cardNumber: String = "---- ---- ---- ----",
    val cardType: String = "------",
    val amount: Int = 0,
    val cardStatus: String = "----",
    val imageId: Int = if (cardStatus == "activa") {
        R.drawable.tullave_card_3
    } else {
        R.drawable.tullave_card_dont_exist
    },

    )