package com.example.keystore_hugolopezfernandez.vista.pantallas.perfilDato

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.keystore_hugolopezfernandez.R
import com.example.keystore_hugolopezfernandez.modelo.DatosPrivados
import com.example.keystore_hugolopezfernandez.navegacion.Vistas
import com.example.keystore_hugolopezfernandez.viewmodel.PerfilDatoVM
import com.example.keystore_hugolopezfernandez.vista.componentes.DialogoModalMasKeyStore
import com.example.keystore_hugolopezfernandez.vista.componentes.MiniTarjetaFuncionDato
import com.example.keystore_hugolopezfernandez.vista.componentes.TarjetaDatosKeyStore
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilDato(navController: NavController, viewModel: PerfilDatoVM, id: String, uid: String) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var tarjetaAbierta by remember { mutableStateOf(false) }
    val dialogoModal = rememberModalBottomSheetState()
    val cargando : Boolean by viewModel.cargando.observeAsState(initial = false)
    val datoPrivado: DatosPrivados by viewModel.datoPrivado.observeAsState(DatosPrivados("", "", emptyList(), ""))
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getDatoPrivado(id, uid)
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
        if (tarjetaAbierta) {
            DialogoModalMasKeyStore(
                alCerrar = { tarjetaAbierta = false },
                estadoDialogo = dialogoModal,
                funcionTarjeta1 = {},
                funcionTarjeta2 = {
                    scope.launch {
                        viewModel.deleteDatoPrivado(id, uid, navController)
                    }
                })
        }

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
                            text = datoPrivado.titulo,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    actions = {
                        IconButton(onClick = {navController.navigate(Vistas.CrearContra.ruta + "?id=" + id + "&uid=" + uid)}) {
                            Icon(
                                imageVector = Icons.Rounded.Edit,
                                contentDescription = "editar",
                                tint = colorResource(id = R.color.blanco_KeyStore),
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(Vistas.Coleccion.ruta + "?uid=" + uid) }) {
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
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(colorResource(id = R.color.azul_apagado_KeyStore)),
            ) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                    Spacer(modifier = Modifier.height(35.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        IconButton(
                            onClick = {}, modifier = Modifier
                                .size(100.dp)
                                .clip(
                                    CardDefaults.elevatedShape
                                )
                                .background(colorResource(id = R.color.blanco_KeyStore))
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Info,
                                contentDescription = "atras",
                                tint = colorResource(id = R.color.azul_KeyStore),
                                modifier = Modifier.size(90.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(35.dp))
                    LazyRow(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        content = {
                            item {
                                MiniTarjetaFuncionDato(
                                    alClickar = { /*TODO*/ },
                                    icono = Icons.Rounded.Share,
                                    textoTarjeta = "Compartir"
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.width(35.dp))
                            }
                            item {
                                MiniTarjetaFuncionDato(
                                    alClickar = { tarjetaAbierta = true },
                                    icono = Icons.Rounded.MoreVert,
                                    textoTarjeta = "Más"
                                )
                            }
                        })
                    Spacer(modifier = Modifier.height(35.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Box(Modifier.fillMaxSize()) {
                            LazyColumn(
                                Modifier
                                    .fillMaxSize()
                                    .padding(start = 20.dp, end = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp), content = {
                                    items(datoPrivado.items.size) { indice ->
                                        TarjetaDatosKeyStore(tituloDato = datoPrivado.items[indice].nombre, notaDato = datoPrivado.items[indice].contenido) {

                                        }
                                    }
                                })
                        }
                    }
                }
            }
        }
    }
}