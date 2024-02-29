package com.example.keystore_hugolopezfernandez.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.keystore_hugolopezfernandez.modelo.DatosPrivados
import com.example.keystore_hugolopezfernandez.modelo.RespuestaApi
import com.example.keystore_hugolopezfernandez.navegacion.Vistas
import com.example.keystore_hugolopezfernandez.retrofit.InstanciaRetrofit.RetrofitInstance.api
import com.toxicbakery.bcrypt.Bcrypt
import kotlinx.coroutines.launch

class CrearTarjetaVM : ViewModel() {
    private val _datoPrivado = MutableLiveData<DatosPrivados>()
    val datoPrivado: LiveData<DatosPrivados> = _datoPrivado

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

    private val _contenidoItem4 = MutableLiveData<String>()
    val contenidoItem4: LiveData<String> = _contenidoItem4

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo: LiveData<Boolean> = _botonActivo

    private val _cargando = MutableLiveData<Boolean>()
    val cargando: LiveData<Boolean> = _cargando

    fun CambiarInputs(
        titulo: String,
        nota: String,
        contenidoItem1: String,
        contenidoItem2: String,
        contenidoItem3: String,
        contenidoItem4: String,
    ) {
        _titulo.value = titulo
        _nota.value = nota
        _contenidoItem1.value = contenidoItem1
        _contenidoItem2.value = contenidoItem2
        _contenidoItem3.value = contenidoItem3
        _contenidoItem4.value = contenidoItem4
        _botonActivo.value = EsTituloValido(titulo) && EsNotaValido(nota) && SonLosItemsValidos(
            contenidoItem1,
            contenidoItem2,
            contenidoItem3,
            contenidoItem4
        )
    }

    private fun EsTituloValido(titulo: String): Boolean = titulo.isNotEmpty()

    private fun EsNotaValido(nota: String): Boolean = nota.isNotEmpty()

    private fun SonLosItemsValidos(
        contenidoItem1: String,
        contenidoItem2: String,
        contenidoItem3: String,
        contenidoItem4: String,
    ): Boolean = contenidoItem1.isNotEmpty() && contenidoItem2.isNotEmpty() && contenidoItem3.isNotEmpty() && contenidoItem4.isNotEmpty()

    suspend fun getDatoPrivado(id: String, uid: String) {
        _cargando.value = true
        try {
            val result = api.getDatoPrivado(id, uid)
            if (result.isSuccessful) {
                val response: RespuestaApi<DatosPrivados> = result.body()!!
                if (response.exito == true){
                    _datoPrivado.value = response.datos!!
                    RellenarCampos()
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getDatoPrivado()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }

    fun RellenarCampos(){
        _titulo.value = _datoPrivado.value?.titulo
        _nota.value = _datoPrivado.value?.nota
        _contenidoItem1.value = _datoPrivado.value?.items?.get(0)?.contenido
        _contenidoItem2.value = _datoPrivado.value?.items?.get(1)?.contenido
        _contenidoItem3.value = _datoPrivado.value?.items?.get(2)?.contenido
        _contenidoItem4.value = _datoPrivado.value?.items?.get(3)?.contenido
    }

    suspend fun postDatoPrivado(navController: NavController, uid: String, datosPrivados: DatosPrivados) {
        _cargando.value = true
        try {
            val result = api.postDatoPrivado(datosPrivados)
            if (result.isSuccessful) {
                val response: RespuestaApi<DatosPrivados> = result.body()!!
                if (response.exito == true){
                    Log.e("ViewModel", "postDatosPrivados() con éxito, resultado: $result")
                    navController.navigate(Vistas.Coleccion.ruta + "?uid=" + uid)
                } else {
                    navController.navigate(Vistas.CrearContra.ruta + "?uid=" + uid)
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en postDatosPrivados()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }

    suspend fun putDatoPrivado(navController: NavController, id: String, uid: String, datosPrivados: DatosPrivados) {
        _cargando.value = true
        try {
            val result = api.putDatoPrivado(id, datosPrivados)
            if (result.isSuccessful) {
                val response: RespuestaApi<DatosPrivados> = result.body()!!
                if (response.exito == true){
                    Log.e("ViewModel", "putDatosPrivados() con éxito, resultado: $result")
                    navController.navigate(Vistas.Coleccion.ruta + "?uid=" + uid)
                } else {
                    navController.navigate(Vistas.CrearContra.ruta + "?id=" + id + "&uid=" + uid)
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en putDatosPrivados()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }
}