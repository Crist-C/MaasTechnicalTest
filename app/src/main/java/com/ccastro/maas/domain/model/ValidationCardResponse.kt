package com.ccastro.maas.domain.model

data class ValidationCardResponse (
    val card: String,
    val isValid: Boolean,
    val status: String,
    val statusCode: Int
)