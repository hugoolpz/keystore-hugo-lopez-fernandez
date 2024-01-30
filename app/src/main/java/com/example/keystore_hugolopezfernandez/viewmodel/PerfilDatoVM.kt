package com.example.keystore_hugolopezfernandez.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.keystore_hugolopezfernandez.modelo.DatosPrivados
import com.example.keystore_hugolopezfernandez.modelo.RespuestaApi
import com.example.keystore_hugolopezfernandez.retrofit.InstanciaRetrofit.RetrofitInstance.api

class PerfilDatoVM: ViewModel() {
    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    private val _datoPrivado = MutableLiveData<DatosPrivados>()
    val datoPrivado: LiveData<DatosPrivados> = _datoPrivado

    suspend fun getDatoPrivado(id: String, uid: String) {
        _cargando.value = true
        try {
            val result = api.getDatoPrivado(id, uid)
            if (result.isSuccessful) {
                val response: RespuestaApi<DatosPrivados> = result.body()!!
                if (response.exito == true){
                    _datoPrivado.value = response.datos!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getDatosPrivados()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }
}