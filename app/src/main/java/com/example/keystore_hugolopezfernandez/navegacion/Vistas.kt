package com.example.keystore_hugolopezfernandez.navegacion

sealed class Vistas(val ruta: String) {
    object Inicio: Vistas("Inicio");
    object Registro: Vistas("Registro");
    object Coleccion: Vistas("Coleccion");
    object CrearDato: Vistas("CrearDato");
    object PerfilDato: Vistas("PerfilDato");
}