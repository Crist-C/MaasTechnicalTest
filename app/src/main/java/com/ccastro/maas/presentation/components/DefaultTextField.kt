package com.ccastro.maas.presentation.components


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField (
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    hideText: Boolean = false
){
    //val icon = Printer(R.drawable.)
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {onValueChange(it)},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        label = {
            Text(text = label)
        },
        maxLines = 1,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondary
            )
        },
        visualTransformation = if(hideText) PasswordVisualTransformation() else VisualTransformation.None

    )
}