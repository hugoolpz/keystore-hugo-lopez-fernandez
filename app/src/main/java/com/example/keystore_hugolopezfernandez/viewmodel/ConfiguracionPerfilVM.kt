package com.example.keystore_hugolopezfernandez.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.keystore_hugolopezfernandez.navegacion.Vistas
import com.example.keystore_hugolopezfernandez.retrofit.InstanciaRetrofit
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class ConfiguracionPerfilVM: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    fun EliminarCuenta(navController: NavController){
        viewModelScope.launch {
            auth.currentUser?.delete()
            _cargando.value = true
            navController.navigate(Vistas.Inicio.ruta)
        }
    }
}