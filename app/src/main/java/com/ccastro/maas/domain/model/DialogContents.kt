package com.ccastro.maas.domain.model

sealed class DialogContents(val title: String, val description: String, var onCallFunction: () -> Unit) {

    object Agregar : DialogContents("No tienes ninguna tarjeta", "¿Deseas agregar una?", {})
    object Eliminar : DialogContents("Eliminar tarjeta", "¿Deseas eliminar la siguiente tarjeta?\n", {})

}