package com.example.keystore_hugolopezfernandez.vista.pantallas.coleccion

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.keystore_hugolopezfernandez.R
import com.example.keystore_hugolopezfernandez.modelo.DatosPrivados
import com.example.keystore_hugolopezfernandez.navegacion.Vistas
import com.example.keystore_hugolopezfernandez.viewmodel.ColeccionVM
import com.example.keystore_hugolopezfernandez.vista.componentes.BarraBusquedaKeyStore
import com.example.keystore_hugolopezfernandez.vista.componentes.DialogoCreacionCarpetaKeyStore
import com.example.keystore_hugolopezfernandez.vista.componentes.DialogoModalCreacionKeyStore
import com.example.keystore_hugolopezfernandez.vista.componentes.ItemMenuIzqKeyStore
import com.example.keystore_hugolopezfernandez.vista.componentes.TarjetaDatosKeyStore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Coleccion(navController: NavController, viewModel: ColeccionVM, uid: String) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var tarjetaAbierta by remember { mutableStateOf(false) }
    var dialogoAbiertoCarpeta by remember { mutableStateOf(false) }
    val dialogoModal = rememberModalBottomSheetState()
    val estadoMenu = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val datosPrivados: List<DatosPrivados> by viewModel.datosPrivados.observeAsState(emptyList())
    val cargando: Boolean by viewModel.cargando.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        viewModel.getDatosPrivados(uid)
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
        Log.d("datosPrivados", datosPrivados.toString())

        if (dialogoAbiertoCarpeta) {
            tarjetaAbierta = false
            DialogoCreacionCarpetaKeyStore(
                valor = "",
                alCambiarValor = {},
                modifier = Modifier.fillMaxWidth(),
                alRechazar = { dialogoAbiertoCarpeta = false },
                alConfirmar = { dialogoAbiertoCarpeta = false },
                tituloDialogo = "Crea una nueva carpeta:"
            )
        }

        if (tarjetaAbierta) {
            DialogoModalCreacionKeyStore(
                alCerrar = { tarjetaAbierta = false },
                estadoDialogo = dialogoModal,
                funcionTarjeta1 = {
                    navController.navigate(
                        Vistas.CrearDato.ruta
                    )
                },
                funcionTarjeta4 = { dialogoAbiertoCarpeta = true })
        }

        ModalNavigationDrawer(
            drawerState = estadoMenu,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = colorResource(id = R.color.blanco_KeyStore),
                    modifier = Modifier.width(300.dp)
                ) {
                    Column {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colorResource(id = R.color.azul_oscuro_KeyStore)),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Menu de KeyStore",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(15.4.dp)
                                    .fillMaxWidth(),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.blanco_KeyStore)
                            )
                        }
                        LazyColumn(content = {
                            item {
                                ItemMenuIzqKeyStore(
                                    icono = Icons.Rounded.Info,
                                    textoItem = "Tus datos privados",
                                    true,
                                    funcionItem = { })
                            }
                            item {
                                ItemMenuIzqKeyStore(
                                    icono = Icons.Rounded.Settings,
                                    textoItem = "Configurar tu perfil",
                                    false,
                                    funcionItem = { })
                            }
                            item {
                                ItemMenuIzqKeyStore(
                                    icono = Icons.Rounded.ExitToApp,
                                    textoItem = "Cerrar sesión",
                                    false,
                                    funcionItem = { navController.navigate(Vistas.Inicio.ruta) })
                            }
                        })
                        Image(
                            painter = painterResource(id = R.drawable.secreto_menu_keystore),
                            contentDescription = "",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .padding(top = 400.dp)
                                .size(350.dp)
                        )
                    }
                }
            },
        ) {
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
                                text = "Tus datos privados:",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    estadoMenu.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Rounded.Menu,
                                    contentDescription = "menu",
                                    tint = colorResource(id = R.color.blanco_KeyStore),
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { tarjetaAbierta = true },
                        shape = CircleShape,
                        contentColor = colorResource(id = R.color.blanco_KeyStore),
                        containerColor = colorResource(id = R.color.azul_oscuro_KeyStore)
                    ) {
                        Icon(Icons.Rounded.Add, "nuevo", modifier = Modifier.size(40.dp))
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(colorResource(id = R.color.azul_apagado_KeyStore))
                ) {
                    LazyColumn(verticalArrangement = spacedBy(10.dp), modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 5.dp, bottom = 5.dp, start = 16.dp, end = 16.dp),
                        content = {
                            item {
                                BarraBusquedaKeyStore(
                                    busqueda = "",
                                    alCambiarValor = {},
                                    alBuscar = {},
                                    estaBuscando = false,
                                    alCambiarDispo = {},
                                    placeholder = {
                                        Text(
                                            text = "Buscar...",
                                            color = colorResource(id = R.color.azul_KeyStore)
                                        )
                                    },
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Rounded.Search,
                                            contentDescription = "buscar",
                                            tint = colorResource(id = R.color.azul_KeyStore),
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }
                                )
                            }
                            items(datosPrivados.size) { indice ->
                                TarjetaDatosKeyStore(datosPrivados[indice].titulo, datosPrivados[indice].nota, funcionTarjeta = {
                                    viewModel.verDato(navController, datosPrivados[indice]._id, uid)
                                })
                            }
                        })
                }
            }
        }
    }
}