package com.ccastro.maas.presentation.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField (
    modifier: Modifier,
    label: String,
    icon: ImageVector,
    hideText: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    value: String,
    onValueChange: (value: String) -> Unit,
    onValidateData: (value: String) -> Unit = {},
    errorMsg: String = ""
){
    Column() {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = {onValueChange(it)
                            onValidateData(it)},
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            label = {
                Text(text = label)
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            visualTransformation = if(hideText) PasswordVisualTransformation() else VisualTransformation.None,
        )
        Text(
            modifier = modifier.padding(top = 2.dp),
            text = errorMsg,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}