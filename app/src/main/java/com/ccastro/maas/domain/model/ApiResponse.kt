package com.ccastro.maas.domain.model

data class ApiResponse (
    val result: List<Results> = emptyList(),
)

data class Results(
    val userCard: UserCard,
    val validationCardResponse: ValidationCardResponse
)