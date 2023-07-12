package com.ccastro.maas.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DefaultAlertDialog(
    showDialog: Boolean? = false,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    title: String = "Alert",
    textDialog: String = "Text dialog"
) {
    if(showDialog == true){
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(textDialog)
            },
            confirmButton = {
                Button(

                    onClick = {
                        onConfirm()
                    }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismiss()
                    }) {
                    Text("Cancelar")
                }
            }
        )
    }

}
