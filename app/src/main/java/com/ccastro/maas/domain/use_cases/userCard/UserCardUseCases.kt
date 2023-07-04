package com.ccastro.maas.domain.use_cases.userCard

data class UserCardUseCases (
    val saveCard: SaveCard,
    val readCardByField: ReadCardByField,
    val readCardById: ReadCardById
)