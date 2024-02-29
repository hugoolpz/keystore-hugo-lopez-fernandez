package com.example.keystore_hugolopezfernandez.vista.pantallas.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.keystore_hugolopezfernandez.R
import com.example.keystore_hugolopezfernandez.viewmodel.RegistroVM
import com.example.keystore_hugolopezfernandez.vista.componentes.BotonTonalKeyStore
import com.example.keystore_hugolopezfernandez.vista.componentes.InputDelineadoKeystore

@Composable
fun Registro(navController: NavController, viewModel: RegistroVM){
    val correo : String by viewModel.correo.observeAsState(initial = "")
    val contra : String by viewModel.contra.observeAsState(initial = "")
    val botonActivo : Boolean by viewModel.botonActivo.observeAsState(initial = false)
    val cargando : Boolean by viewModel.cargando.observeAsState(initial = false)

    if (cargando){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.azul_apagado_KeyStore)),
            contentAlignment = Alignment.Center)
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Image(painter = painterResource(id = R.drawable.imagen_cargando), contentDescription = "",                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(380.dp))
                LinearProgressIndicator(
                    modifier = Modifier.padding(10.dp).height(15.dp),
                    color = colorResource(id = R.color.azul_KeyStore),
                    trackColor = colorResource(id = R.color.azul_claro_KeyStore)
                )
            }
        }
    } else {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.azul_apagado_KeyStore))) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_keystore),
                    contentDescription = "logo_inicio",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(270.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(colorResource(id = R.color.blanco_KeyStore)),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                    ) {
                        Text(
                            text = "¡Bienvenido a KeyStore!",
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.headlineLarge,
                            fontFamily = FontFamily.Serif,
                            color = colorResource(id = R.color.negro_KeyStore),
                            modifier = Modifier.fillMaxWidth().padding(8.dp, 5.dp, 8.dp, 5.dp).fillMaxWidth()
                        )
                        Text(
                            text = "Regístrate:",
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.headlineSmall,
                            fontFamily = FontFamily.Serif,
                            color = colorResource(id = R.color.azul_KeyStore),
                            modifier = Modifier.padding(8.dp, 5.dp, 8.dp, 5.dp)
                        )
                        InputDelineadoKeystore(
                            valor = correo,
                            alCambiarValor = {viewModel.CambiarInputs(it, contra)},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp, 10.dp, 8.dp, 10.dp),
                            etiqueta = { Text(text = "Correo") },
                            placeholder = { Text(text = "Un correo...") },
                            esContra = false
                        )
                        InputDelineadoKeystore(
                            valor = contra,
                            alCambiarValor = {viewModel.CambiarInputs(correo, it)},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp, 10.dp, 8.dp, 10.dp),
                            etiqueta = { Text(text = "Contraseña") },
                            placeholder = { Text(text = "Una contraseña...") },
                            esContra = true
                        )
                        BotonTonalKeyStore(
                            alClickar = { viewModel.IntentarRegistrarse(correo, contra, navController) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp, 10.dp, 10.dp, 8.dp),
                            valorTexto = "REGISTRARSE",
                            estaActivo = botonActivo
                        )
                    }
                }
            }
        }
    }
}