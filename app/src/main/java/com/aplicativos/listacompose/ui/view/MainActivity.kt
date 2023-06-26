package com.aplicativos.listacompose.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aplicativos.listacompose.ui.theme.ListaComposeTheme

/**
 * Created by João Bosco on 26/06/2023.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaComposeTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "TaskList") {
                    composable(
                        route = "TaskList"
                    ) {
                        TaskList(navController)
                    }
                    composable(
                        route = "TaskSave"
                    ) {
                        TaskSave(navController)
                    }
                }
            }
        }
    }
}
