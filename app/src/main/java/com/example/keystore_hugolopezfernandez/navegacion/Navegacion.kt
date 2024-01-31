package com.example.keystore_hugolopezfernandez.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.keystore_hugolopezfernandez.viewmodel.ColeccionVM
import com.example.keystore_hugolopezfernandez.viewmodel.CrearContraVM
import com.example.keystore_hugolopezfernandez.vista.pantallas.coleccion.Coleccion
import com.example.keystore_hugolopezfernandez.vista.pantallas.crearDato.CrearContra
import com.example.keystore_hugolopezfernandez.vista.pantallas.inicio.Inicio
import com.example.keystore_hugolopezfernandez.viewmodel.InicioVM
import com.example.keystore_hugolopezfernandez.viewmodel.PerfilDatoVM
import com.example.keystore_hugolopezfernandez.viewmodel.RegistroVM
import com.example.keystore_hugolopezfernandez.vista.pantallas.perfilDato.PerfilDato
import com.example.keystore_hugolopezfernandez.vista.pantallas.registro.Registro

@Composable
fun SistemaNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Vistas.Inicio.ruta) {
        composable("${Vistas.Inicio.ruta}") {
            Inicio(
                navController = navController,
                viewModel = InicioVM()
            )
        }
        composable("${Vistas.Registro.ruta}") {
            Registro(
                navController = navController,
                viewModel = RegistroVM()
            )
        }
        composable(
            route = "${Vistas.Coleccion.ruta}?uid={uid}",
            arguments = listOf(navArgument(name = "uid") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("uid")
                ?.let { uid ->
                    Coleccion(
                        viewModel = ColeccionVM(),
                        navController = navController,
                        uid = uid
                    )
                }
        }
        composable(
            route = "${Vistas.CrearContra.ruta}?id={id}&uid={uid}",
            arguments = listOf(
                navArgument(name = "id") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                },
                navArgument(name = "uid") {
                    type = NavType.StringType
                })
        ) { navBackStackEntry ->
            val id: String = navBackStackEntry.arguments?.getString("id") ?: ""
            val uid = navBackStackEntry.arguments?.getString("uid") ?: ""

            CrearContra(
                navController = navController,
                viewModel = CrearContraVM(),
                id = id,
                uid = uid
            )
        }
        composable(
            route = "${Vistas.PerfilDato.ruta}?id={id}&uid={uid}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.StringType
            }, navArgument(name = "uid") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")
            val uid = navBackStackEntry.arguments?.getString("uid")

            id?.let { valorId ->
                uid?.let { valorUid ->
                    PerfilDato(
                        navController = navController,
                        viewModel = PerfilDatoVM(),
                        id = valorId,
                        uid = valorUid
                    )
                }
            }
        }
    }
}