package com.ccastro.maas.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.ccastro.maas.presentation.ui.theme.MaasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    title: String,
    titleStyle: TextStyle = MaterialTheme.typography.titleSmall,
    upAvailable: Boolean = false,
    navHostController: NavHostController? = null
){
    Surface(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    style = titleStyle
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(),
            navigationIcon = {
                if(upAvailable){
                    IconButton(
                        onClick = { navHostController?.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back",
                            tint = iconColor)
                    }
                }
            }
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultTopBarPreview(){
    MaasTheme{
        DefaultTopBar(title =  "Nuevo Usuario", upAvailable = true)
    }
}