package com.example.keystore_hugolopezfernandez.modelo

data class DatosPrivados(
    val _id: String,
    val titulo: String,
    val nota: String,
    val items: List<ItemsDatos>,
    val idUsuario: String
)