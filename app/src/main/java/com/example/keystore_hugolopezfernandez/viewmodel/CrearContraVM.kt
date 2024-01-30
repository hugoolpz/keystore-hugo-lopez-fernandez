package com.example.keystore_hugolopezfernandez.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keystore_hugolopezfernandez.modelo.ItemsDatos

class CrearContraVM : ViewModel() {
    private val _titulo = MutableLiveData<String>()
    val titulo: LiveData<String> = _titulo

    private val _nota = MutableLiveData<String>()
    val nota: LiveData<String> = _nota

    private val _contenidoItem1 = MutableLiveData<String>()
    val contenidoItem1: LiveData<String> = _contenidoItem1

    private val _contenidoItem2 = MutableLiveData<String>()
    val contenidoItem2: LiveData<String> = _contenidoItem2

    private val _contenidoItem3 = MutableLiveData<String>()
    val contenidoItem3: LiveData<String> = _contenidoItem3

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo: LiveData<Boolean> = _botonActivo

    private val _cargando = MutableLiveData<Boolean>()
    val cargando: LiveData<Boolean> = _cargando

    fun CambiarInputs(
        titulo: String,
        nota: String,
        contenidoItem1: String,
        contenidoItem2: String,
        contenidoItem3: String
    ) {
        _titulo.value = titulo
        _nota.value = nota
        _contenidoItem1.value = contenidoItem1
        _contenidoItem2.value = contenidoItem2
        _contenidoItem3.value = contenidoItem3
        _botonActivo.value = EsTituloValido(titulo) && EsNotaValido(nota) && SonLosItemsValidos(
            contenidoItem1,
            contenidoItem2,
            contenidoItem3
        )
    }

    private fun EsTituloValido(titulo: String): Boolean = titulo.isNotEmpty()

    private fun EsNotaValido(nota: String): Boolean = nota.isNotEmpty()

    private fun SonLosItemsValidos(
        contenidoItem1: String,
        contenidoItem2: String,
        contenidoItem3: String
    ): Boolean = contenidoItem1.isNotEmpty() && contenidoItem2.isNotEmpty() && contenidoItem3.isNotEmpty()
}