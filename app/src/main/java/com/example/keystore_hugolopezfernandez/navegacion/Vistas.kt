package com.example.keystore_hugolopezfernandez.navegacion

sealed class Vistas(val ruta: String) {
    object Inicio: Vistas("Inicio");
    object Registro: Vistas("Registro");
    object Coleccion: Vistas("Coleccion");
    object CrearContra: Vistas("CrearContra");
    object CrearTarjeta: Vistas("CrearTarjeta");
    object PerfilDato: Vistas("PerfilDato");
}