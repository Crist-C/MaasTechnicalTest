package com.ccastro.maas.domain.use_cases.userCard

data class UserCardUseCases (
    val addUserCard: AddUserCard,
    val saveCard: SaveCard,
    val getAllCard: GetAllCards,
    val deleteCard: DeleteCard,
    val totalUserCards: TotalUserCards
)