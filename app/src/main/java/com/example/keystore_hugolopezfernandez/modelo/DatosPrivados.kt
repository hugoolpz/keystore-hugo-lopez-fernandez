package com.example.keystore_hugolopezfernandez.modelo

data class DatosPrivados(
    val titulo: String,
    val nota: String,
    val items: List<ItemsDatos>,
    val idUsuario: String
){
    val _id: String = ""
}