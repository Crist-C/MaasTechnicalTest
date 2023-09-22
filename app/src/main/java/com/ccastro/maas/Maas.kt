package com.ccastro.maas

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Injección de dependencias.
 *
 * 1. Agregar las dependencias según la documentación:
 *      https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419#kts
 *      -> tener en cuenta que kotlin("kapt") está mal, debería ser id("kotlin-kapt")
 *
 * 2. Crear la clase con el mismo nombre de la app en el directorio raiz (esta clase)
 *    y anotarla con @HiltAndroidApp
 *
 * 3. En el manifest agregar la propiedad android:name=".NOMBRE_DE_LA_CLASE_CREADA_EN_EL_PASO_ANTERIOR"
 * 4. Agregar las dependencias de navegación desde https://developer.android.com/jetpack/androidx/releases/hilt?hl=es-419#hilt-navigation-compose-1.0.0
 *      la dependencia en este caso es implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
 *
 * 5. Anotar las clases ViewModel con @HiltViewModel y el constructor con @Inject incluso si es vacío,
 *    finalmente también debe heredar de VievModel()
 *      ejemplo:
 *
 *      @HiltViewModel
 *      class LoginViewModel @Inject constructor(): ViewModel() {}
 *
 * 6. Anotar la MainActivity, que es la activity principal en donde inica la app con la
 *    anotación @AndroidEntryPoint.
 *
 * 7. Ya puedes injectar el ViewModel en donde lo necesites y debes inicializarlo con hiltViewModel
 *      ejemplo:
 *      viewModel: LoginViewModel = hiltViewModel()
 */
@HiltAndroidApp
class Maas: Application() {

    /**
     *  1010000008551426
        1010000008553091
        1010000008553067   Disabled
        1010000008550436
        1010000008582546
     */
}