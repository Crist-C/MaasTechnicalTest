package com.ccastro.maas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ccastro.maas.screens.mainMenu.MainMenuScreen
import com.ccastro.maas.screens.mainMenu.components.DemoCardList
import com.ccastro.maas.screens.mainMenu.components.demoStoppinPlaceList
import com.ccastro.maas.ui.theme.MaasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainMenuScreen(userCards = DemoCardList(), stoppingPlace = demoStoppinPlaceList())
                }
            }
        }
    }
}
