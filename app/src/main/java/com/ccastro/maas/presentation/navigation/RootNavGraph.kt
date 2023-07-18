package com.ccastro.maas.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ccastro.maas.presentation.MainActivity

@Composable
fun RootNavGraph(navHostController: NavHostController, activity: MainActivity) {
    /**
     * Este RootNavGraph, es un Grafo de Grafos
     */
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.INTRODUCE
    ){

        /**
         * Se declaran de esta manera ya que estos son los gráficos
         * principales.
         * No se llaman con una Sealed Class ya que NO están declarados cómo objetos Composables
         * y solo los composable se referencian por objetos de la Sealed Class.
         *
         * Para navergar entre ellos se hace a travez del objeto Graph
         * Example: Graph.HOME, Graph.INTRODUCE, Graph.AUTHENTICATON
         */
        introduceNavGraph(navHostController = navHostController)
        authNavGrap(navHostController = navHostController)
        homeNavGrap(navHostController = navHostController, activity)

    }
}