package com.example.keystore_hugolopezfernandez.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.keystore_hugolopezfernandez.modelo.DatosPrivados
import com.example.keystore_hugolopezfernandez.modelo.RespuestaApi
import com.example.keystore_hugolopezfernandez.navegacion.Vistas
import com.example.keystore_hugolopezfernandez.retrofit.InstanciaRetrofit
import kotlinx.coroutines.launch

class ColeccionVM: ViewModel() {
    private val api = InstanciaRetrofit.RetrofitInstance.api

    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    private val _datosPrivados = MutableLiveData<List<DatosPrivados>>()
    val datosPrivados: LiveData<List<DatosPrivados>> = _datosPrivados

    suspend fun getDatosPrivados(uid: String) {
        _cargando.value = true
        try {
            val result = api.getDatosPrivados(uid)
            if (result.isSuccessful) {
                val response: RespuestaApi<List<DatosPrivados>> = result.body()!!
                if (response.exito == true){
                    _datosPrivados.value = response.datos!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getDatosPrivados()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }

    fun verDato(navController: NavController, id: String, uid: String){
        _cargando.value = true
        navController.navigate(Vistas.PerfilDato.ruta + "/" + id + "/" + uid)
    }
}