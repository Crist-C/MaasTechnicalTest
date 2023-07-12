package com.ccastro.maas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ccastro.maas.R

@Entity(tableName = "userCards")
data class UserCard(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "cardNumber") val cardNumber: String = "---- ---- ---- ----",
    @ColumnInfo(name = "profileCode") val profileCode: String = "",
    @ColumnInfo(name = "profile") val profile: String = "------",
    @ColumnInfo(name = "profile_es") val profile_es: String = "------",
    @ColumnInfo(name = "bankCode") val bankCode: String = "--",
    @ColumnInfo(name = "bankName") val bankName: String = "---------------",
    @ColumnInfo(name = "userName") val userName: String = "---------",
    @ColumnInfo(name = "userLastName") val userLastName: String = "---------",

    @ColumnInfo(name = "card") var card: String? = "",
    @ColumnInfo(name = "isValid") var isValid: Boolean? = false,
    @ColumnInfo(name = "status") var status: String? = "",
    @ColumnInfo(name = "statusCode") var statusCode: Int? = 0,

    @ColumnInfo(name = "currentUserId") var currentUserId: String = "",
    @ColumnInfo(name = "amount") val amount: Int = 0,
    @ColumnInfo(name = "imageId") val imageId: Int = if (isValid == true) {
        R.drawable.tullave_card_3
    } else {
        R.drawable.tullave_card_dont_exist
    }

    )