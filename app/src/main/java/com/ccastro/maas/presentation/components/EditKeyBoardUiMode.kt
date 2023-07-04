package com.ccastro.maas.presentation.components

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView

@Composable
fun EditKeyBoardUiMode(keyboardUiMode: Int) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.setSoftInputMode(keyboardUiMode)
        }
    }
}