package com.ccastro.maas.domain.model

data class User (
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var passwordConfirm: String = ""
)