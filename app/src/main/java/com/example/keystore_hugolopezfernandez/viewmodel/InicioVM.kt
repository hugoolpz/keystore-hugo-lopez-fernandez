package com.example.keystore_hugolopezfernandez.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.keystore_hugolopezfernandez.navegacion.Vistas
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import java.lang.Exception

class InicioVM: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    private val _correo = MutableLiveData<String>()
    val correo : LiveData<String> = _correo

    private val _contra = MutableLiveData<String>()
    val contra : LiveData<String> = _contra

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    fun CambiarInputs(correo: String, contra: String){
        _correo.value = correo
        _contra.value = contra
        _botonActivo.value = EsCorreoValido(correo) && EsContraValida(contra)
    }

    private fun EsCorreoValido(correo: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(correo).matches()

    private fun EsContraValida(contra: String): Boolean = contra.length > 6

    fun IntentarInicioSesion(correo: String, contra: String, navController: NavController){
        _cargando.value = true
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(correo, contra)
                    .addOnSuccessListener {
                        navController.navigate(Vistas.Coleccion.ruta + "/" + auth.currentUser?.uid)
                        _cargando.value = false
                    }
                    .addOnFailureListener {
                        _cargando.value = false
                    }
            } catch (ex: Exception) {
                Log.d("authFailed", ex.message.toString())
            }
        }
    }
}