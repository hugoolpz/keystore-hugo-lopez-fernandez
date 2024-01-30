package com.example.keystore_hugolopezfernandez.vista.pantallas.crearDato

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.keystore_hugolopezfernandez.R
import com.example.keystore_hugolopezfernandez.viewmodel.CrearContraVM
import com.example.keystore_hugolopezfernandez.vista.componentes.BotonTonalKeyStore
import com.example.keystore_hugolopezfernandez.vista.componentes.InputDelineadoKeystore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearDato(navController: NavController, viewModel: CrearContraVM) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val titulo : String by viewModel.titulo.observeAsState(initial = "")
    val nota : String by viewModel.nota.observeAsState(initial = "")
    val contenidoItem1 : String by viewModel.contenidoItem1.observeAsState(initial = "")
    val contenidoItem2 : String by viewModel.contenidoItem2.observeAsState(initial = "")
    val contenidoItem3 : String by viewModel.contenidoItem3.observeAsState(initial = "")
    val botonActivo : Boolean by viewModel.botonActivo.observeAsState(initial = false)
    val cargando : Boolean by viewModel.cargando.observeAsState(initial = false)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.azul_oscuro_KeyStore),
                    titleContentColor = colorResource(id = R.color.blanco_KeyStore),
                ),
                title = {
                    Text(
                        text = "Nuevos datos personales",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {/*navController.navigate(Vistas.Coleccion.ruta + "/" + uid)*/}) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "atras",
                            tint = colorResource(id = R.color.blanco_KeyStore),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorResource(id = R.color.azul_apagado_KeyStore)),
        ) {
            LazyColumn(Modifier.padding(15.dp, 10.dp, 15.dp, 10.dp), verticalArrangement = Arrangement.spacedBy(15.dp) , content = {
                item {
                    Text(text = "Información de tu dato privado:", fontSize = 18.sp, fontWeight = FontWeight.Bold,)
                    Divider(Modifier.fillMaxWidth(), color = colorResource(id = R.color.azul_oscuro_KeyStore))
                }
                item {
                    InputDelineadoKeystore(
                        valor = titulo,
                        alCambiarValor = { viewModel.CambiarInputs(it, nota, contenidoItem1, contenidoItem2, contenidoItem3) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        etiqueta = { Text(text = "Título del dato") },
                        placeholder = { Text(text = "Un título para el dato...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineadoKeystore(
                        valor = nota,
                        alCambiarValor = {viewModel.CambiarInputs(titulo, it, contenidoItem1, contenidoItem2, contenidoItem3)},
                        modifier = Modifier
                            .fillMaxWidth(),
                        etiqueta = { Text(text = "Nota del dato") },
                        placeholder = { Text(text = "Una nota para el dato...") },
                        esContra = false
                    )
                }
                item {
                    Text(text = "Items de tu dato privado:", fontSize = 18.sp, fontWeight = FontWeight.Bold,)
                    Divider(Modifier.fillMaxWidth(), color = colorResource(id = R.color.azul_oscuro_KeyStore))
                }
                item {
                    InputDelineadoKeystore(
                        valor = contenidoItem1,
                        alCambiarValor = {viewModel.CambiarInputs(titulo, nota, it, contenidoItem2, contenidoItem3)},
                        modifier = Modifier
                            .fillMaxWidth(),
                        etiqueta = { Text(text = "Correo") },
                        placeholder = { Text(text = "El correo de la cuenta...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineadoKeystore(
                        valor = contenidoItem1,
                        alCambiarValor = {viewModel.CambiarInputs(titulo, nota, contenidoItem1, it, contenidoItem3)},
                        modifier = Modifier
                            .fillMaxWidth(),
                        etiqueta = { Text(text = "Contraseña") },
                        placeholder = { Text(text = "La contraseña de la cuenta...") },
                        esContra = false
                    )
                }
                item {
                    InputDelineadoKeystore(
                        valor = contenidoItem1,
                        alCambiarValor = {viewModel.CambiarInputs(titulo, nota, contenidoItem1, contenidoItem2, it)},
                        modifier = Modifier
                            .fillMaxWidth(),
                        etiqueta = { Text(text = "Página web") },
                        placeholder = { Text(text = "La página web...") },
                        esContra = false
                    )
                }
                item {
                    BotonTonalKeyStore(
                        alClickar = { },
                        modifier = Modifier
                            .fillMaxWidth(),
                        valorTexto = "CREAR DATO PRIVADO",
                        estaActivo = botonActivo
                    )
                }
            })
        }
    }
}