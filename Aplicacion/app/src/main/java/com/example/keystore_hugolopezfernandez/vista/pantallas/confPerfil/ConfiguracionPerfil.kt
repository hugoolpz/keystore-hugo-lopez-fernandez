package com.example.keystore_hugolopezfernandez.vista.pantallas.confPerfil

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.keystore_hugolopezfernandez.R
import com.example.keystore_hugolopezfernandez.navegacion.Vistas
import com.example.keystore_hugolopezfernandez.viewmodel.ConfiguracionPerfilVM
import com.example.keystore_hugolopezfernandez.vista.componentes.DialogoEliminacionKeyStore
import com.example.keystore_hugolopezfernandez.vista.componentes.TarjetaDatosKeyStore
import com.google.android.play.integrity.internal.al
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracionPerfil(viewModel: ConfiguracionPerfilVM, navController: NavController, uid: String) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var dialogoAbiertoCarpeta by remember { mutableStateOf(false) }
    val cargando: Boolean by viewModel.cargando.observeAsState(initial = false)

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
        if (dialogoAbiertoCarpeta) {
            DialogoEliminacionKeyStore(
                modifier = Modifier.fillMaxWidth(),
                alRechazar = { dialogoAbiertoCarpeta = false },
                alConfirmar = { viewModel.EliminarCuenta(navController) },
                tituloDialogo = "¿Estás seguro de esto?"
            )
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
                            text = "Tus datos privados:",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(colorResource(id = R.color.azul_apagado_KeyStore))
            ) {
                LazyColumn(verticalArrangement = Arrangement.Absolute.spacedBy(10.dp), modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 5.dp, bottom = 5.dp, start = 16.dp, end = 16.dp),
                    content = {
                        item {
                            TarjetaDatosKeyStore(tituloDato = "Eliminar cuenta", notaDato = "¡Eliminarás tu cuenta para siempre!", icono = Icons.Rounded.Delete, funcionTarjeta = {
                                dialogoAbiertoCarpeta = true
                            })
                        }
                    })
            }
        }
    }
}