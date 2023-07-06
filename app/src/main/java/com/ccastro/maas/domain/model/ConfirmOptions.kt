package com.ccastro.maas.domain.model

sealed class ConfirmOptions(val option : String) {

    object Agregar : ConfirmOptions("agregar")
    object Eliminar : ConfirmOptions("eliminar")
}