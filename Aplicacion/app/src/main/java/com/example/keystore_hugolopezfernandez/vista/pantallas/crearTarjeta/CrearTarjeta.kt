package com.example.keystore_hugolopezfernandez.vista.pantallas.crearContra

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.keystore_hugolopezfernandez.R
import com.example.keystore_hugolopezfernandez.modelo.DatosPrivados
import com.example.keystore_hugolopezfernandez.modelo.ItemsDatos
import com.example.keystore_hugolopezfernandez.navegacion.Vistas
import com.example.keystore_hugolopezfernandez.viewmodel.CrearContraVM
import com.example.keystore_hugolopezfernandez.viewmodel.CrearTarjetaVM
import com.example.keystore_hugolopezfernandez.vista.componentes.BotonTonalKeyStore
import com.example.keystore_hugolopezfernandez.vista.componentes.InputDelineadoKeystore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearTarjeta(navController: NavController, id: String, uid: String, viewModel: CrearTarjetaVM) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val datoPrivado: DatosPrivados by viewModel.datoPrivado.observeAsState(DatosPrivados("", "", emptyList(), ""))
    val titulo: String by viewModel.titulo.observeAsState(initial = "")
    val nota: String by viewModel.nota.observeAsState(initial = "")
    val contenidoItem1: String by viewModel.contenidoItem1.observeAsState(initial = "")
    val contenidoItem2: String by viewModel.contenidoItem2.observeAsState(initial = "")
    val contenidoItem3: String by viewModel.contenidoItem3.observeAsState(initial = "")
    val contenidoItem4: String by viewModel.contenidoItem4.observeAsState(initial = "")
    val botonActivo: Boolean by viewModel.botonActivo.observeAsState(initial = false)
    val cargando: Boolean by viewModel.cargando.observeAsState(initial = false)
    val scope = rememberCoroutineScope()

    if (id != "") {
        LaunchedEffect(Unit) {
            viewModel.getDatoPrivado(id, uid)
        }
    }

    if (cargando) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.azul_apagado_KeyStore)),
            contentAlignment = Alignment.Center
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.imagen_cargando),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(380.dp)
                )
                LinearProgressIndicator(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(15.dp),
                    color = colorResource(id = R.color.azul_KeyStore),
                    trackColor = colorResource(id = R.color.azul_claro_KeyStore)
                )
            }
        }
    } else {
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
                            text = if (id != null) "Nuevos datos personales" else "Actualizar datos personales",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {navController.navigate(Vistas.Coleccion.ruta + "?uid=" + uid) }) {
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
                LazyColumn(
                    Modifier.padding(15.dp, 10.dp, 15.dp, 10.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    content = {
                        item {
                            Text(
                                text = "Información de tu dato privado:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Divider(
                                Modifier.fillMaxWidth(),
                                color = colorResource(id = R.color.azul_oscuro_KeyStore)
                            )
                        }
                        item {
                            InputDelineadoKeystore(
                                valor = titulo,
                                alCambiarValor = {
                                    viewModel.CambiarInputs(
                                        it,
                                        nota,
                                        contenidoItem1,
                                        contenidoItem2,
                                        contenidoItem3,
                                        contenidoItem4
                                    )
                                },
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
                                alCambiarValor = {
                                    viewModel.CambiarInputs(
                                        titulo,
                                        it,
                                        contenidoItem1,
                                        contenidoItem2,
                                        contenidoItem3,
                                        contenidoItem4
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                etiqueta = { Text(text = "Nota del dato") },
                                placeholder = { Text(text = "Una nota para el dato...") },
                                esContra = false
                            )
                        }
                        item {
                            Text(
                                text = "Items de tu dato privado:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Divider(
                                Modifier.fillMaxWidth(),
                                color = colorResource(id = R.color.azul_oscuro_KeyStore)
                            )
                        }
                        item {
                            InputDelineadoKeystore(
                                valor = contenidoItem1,
                                alCambiarValor = {
                                    viewModel.CambiarInputs(
                                        titulo,
                                        nota,
                                        it,
                                        contenidoItem2,
                                        contenidoItem3,
                                        contenidoItem4
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                etiqueta = { Text(text = "Titular de la tarjeta") },
                                placeholder = { Text(text = "El titular...") },
                                esContra = false
                            )
                        }
                        item {
                            InputDelineadoKeystore(
                                valor = contenidoItem2,
                                alCambiarValor = {
                                    viewModel.CambiarInputs(
                                        titulo,
                                        nota,
                                        contenidoItem1,
                                        it,
                                        contenidoItem3,
                                        contenidoItem4
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                etiqueta = { Text(text = "Número de la tarjeta") },
                                placeholder = { Text(text = "El número...") },
                                esContra = false
                            )
                        }
                        item{
                            InputDelineadoKeystore(
                                valor = contenidoItem3,
                                alCambiarValor = {
                                    viewModel.CambiarInputs(
                                        titulo,
                                        nota,
                                        contenidoItem1,
                                        contenidoItem2,
                                        it,
                                        contenidoItem4
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                etiqueta = { Text(text = "Fecha de caducidad") },
                                placeholder = { Text(text = "La fecha...") },
                                esContra = false
                            )
                        }
                        item {
                            InputDelineadoKeystore(
                                valor = contenidoItem4,
                                alCambiarValor = {
                                    viewModel.CambiarInputs(
                                        titulo,
                                        nota,
                                        contenidoItem1,
                                        contenidoItem2,
                                        contenidoItem3,
                                        it
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                etiqueta = { Text(text = "CVV de la tarjeta") },
                                placeholder = { Text(text = "El CVV...") },
                                esContra = false
                            )
                        }
                        item {
                            BotonTonalKeyStore(
                                alClickar = {
                                    val listaItems = listOf(
                                        ItemsDatos("Titular", contenidoItem1, false),
                                        ItemsDatos("Número", contenidoItem2, false),
                                        ItemsDatos("Fecha de caducidad", contenidoItem3, false),
                                        ItemsDatos("CVV", contenidoItem4, false)
                                    )

                                    val dato = DatosPrivados(titulo, nota, listaItems, uid)

                                    scope.launch {
                                        if (id != ""){
                                            viewModel.putDatoPrivado(navController, id, uid, dato)
                                        } else {
                                            viewModel.postDatoPrivado(navController, uid, dato)
                                        }
                                    }

                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                valorTexto = if (id != "") "ACTUALIZAR DATO PRIVADO" else "CREAR DATO PRIVADO",
                                estaActivo = botonActivo
                            )
                        }
                    })
            }
        }
    }
}