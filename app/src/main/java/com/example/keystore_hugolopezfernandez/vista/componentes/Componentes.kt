package com.example.keystore_hugolopezfernandez.vista.componentes

import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keystore_hugolopezfernandez.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDelineadoKeystore(
    valor: String,
    alCambiarValor: (String) -> Unit,
    modifier: Modifier,
    estaActivo: Boolean = true,
    etiqueta: @Composable (() -> Unit),
    placeholder: @Composable (() -> Unit),
    colorLetra: Color = colorResource(id = R.color.negro_KeyStore),
    colorBordeEnfocado: Color = colorResource(id = R.color.azul_KeyStore),
    colorBordeDesenfocado: Color = colorResource(id = R.color.negro_KeyStore),
    colorEtiquetaEnfocada: Color = colorResource(id = R.color.azul_KeyStore),
    colorEtiquetaDesenfocada: Color = colorResource(id = R.color.negro_KeyStore),
    textoAyuda: @Composable (() -> Unit)? = null,
    tipoInput: KeyboardType = KeyboardType.Text,
    esContra: Boolean,
    unaSolaLinea: Boolean = true
) {
    OutlinedTextField(
        value = valor,
        onValueChange = { alCambiarValor(it) },
        modifier = modifier,
        enabled = estaActivo,
        label = etiqueta,
        textStyle = TextStyle(
            color = colorLetra
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = colorResource(id = R.color.blanco_KeyStore),
            focusedBorderColor = colorBordeEnfocado,
            unfocusedBorderColor = colorBordeDesenfocado,
            focusedLabelColor = colorEtiquetaEnfocada,
            unfocusedLabelColor = colorEtiquetaDesenfocada,
        ),
        supportingText = textoAyuda,
        keyboardOptions = if (esContra) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions(
            keyboardType = tipoInput
        ),
        placeholder = placeholder,
        visualTransformation = if (esContra) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = unaSolaLinea
    )
}

@Composable
fun BotonTonalKeyStore(
    alClickar: () -> Unit,
    modifier: Modifier,
    estaActivo: Boolean = true,
    colorContenedor: Color = colorResource(id = R.color.azul_KeyStore),
    colorContenido: Color = colorResource(id = R.color.azul_claro_KeyStore),
    colorContenedorDesactivado: Color = colorResource(id = R.color.azul_claro_KeyStore),
    colorContenidoDesactivado: Color = colorResource(id = R.color.azul_apagado_KeyStore),
    valorTexto: String
) {
    FilledTonalButton(
        onClick = alClickar,
        modifier = modifier,
        enabled = estaActivo,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = colorContenedor,
            contentColor = colorContenido,
            disabledContainerColor = colorContenedorDesactivado,
            disabledContentColor = colorContenidoDesactivado,
        ),
        elevation = ButtonDefaults.filledTonalButtonElevation()
    )
    {
        Text(
            text = valorTexto,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = FontFamily.Serif
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraBusquedaKeyStore(
    busqueda: String,
    alCambiarValor: (String) -> Unit,
    alBuscar: (String) -> Unit,
    estaBuscando: Boolean,
    alCambiarDispo: (Boolean) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = SearchBarDefaults.inputFieldShape,
    colorContenedor: Color = colorResource(id = R.color.blanco_KeyStore),
    colorDivisor: Color = colorResource(id = R.color.azul_KeyStore),
) {
    SearchBar(
        query = busqueda,//text showed on SearchBar
        onQueryChange = alCambiarValor, //update the value of searchText
        onSearch = alBuscar, //the callback to be invoked when the input service triggers the ImeAction.Search action
        active = estaBuscando, //whether the user is searching or not
        onActiveChange = alCambiarDispo,
        placeholder = placeholder,
        trailingIcon = trailingIcon,
        shape = shape,
        colors = SearchBarDefaults.colors(
            containerColor = colorContenedor,
            dividerColor = colorDivisor,
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaDatosKeyStore(
    tituloDato: String,
    notaDato: String,
    funcionTarjeta: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        ElevatedCard(
            onClick = funcionTarjeta,
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .wrapContentHeight()
                .padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.blanco_KeyStore),
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalArrangement = spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Icon(
                        imageVector = Icons.Rounded.Info,
                        contentDescription = "foto_perfil",
                        tint = colorResource(id = R.color.azul_KeyStore),
                        modifier = Modifier.size(40.dp)
                    )
                }
                Column(
                    Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = tituloDato,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = notaDato,
                        color = colorResource(id = R.color.azul_KeyStore),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoModalCreacionKeyStore(
    alCerrar: () -> Unit,
    estadoDialogo: SheetState,
    funcionTarjeta1: () -> Unit,
    funcionTarjeta4: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = alCerrar,
        sheetState = estadoDialogo,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = colorResource(id = R.color.blanco_KeyStore)
    )
    {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
            .padding(start = 10.dp, end = 10.dp),
            verticalArrangement = spacedBy(15.dp),
            content = {
                item {
                    Text(
                        text = "Añadir...",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.drawWithContent {
                            drawContent()
                            drawLine(
                                color = Color(R.color.azul_KeyStore),
                                start = Offset(0f, size.height),
                                end = Offset(700f, size.height),
                                strokeWidth = 5f
                            )
                        }
                    )
                }
                item {
                    ElevatedCard(
                        onClick = funcionTarjeta1,
                        modifier = Modifier.border(
                            1.dp,
                            colorResource(id = R.color.azul_KeyStore),
                            CardDefaults.elevatedShape
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.blanco_KeyStore),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        Text(
                            text = "Contraseña",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        )
                    }
                }
                item {
                    ElevatedCard(
                        modifier = Modifier.border(
                            1.dp,
                            colorResource(id = R.color.azul_KeyStore),
                            CardDefaults.elevatedShape
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.blanco_KeyStore),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        Text(
                            text = "Nota segura",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        )
                    }
                }
                item {
                    ElevatedCard(
                        modifier = Modifier.border(
                            1.dp,
                            colorResource(id = R.color.azul_KeyStore),
                            CardDefaults.elevatedShape
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.blanco_KeyStore),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        Text(
                            text = "Tarjeta de crédito",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        )
                    }
                }
                item {
                    ElevatedCard(
                        onClick = funcionTarjeta4,
                        modifier = Modifier.border(
                            1.dp,
                            colorResource(id = R.color.azul_KeyStore),
                            CardDefaults.elevatedShape
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.blanco_KeyStore),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        Text(
                            text = "Carpeta",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            })
    }
}

@Composable
fun ItemMenuIzqKeyStore(
    icono: ImageVector,
    textoItem: String,
    seleccionado: Boolean,
    funcionItem: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(true, onClick = funcionItem)
            .background(
                if (seleccionado) colorResource(id = R.color.azul_claro_KeyStore) else colorResource(
                    id = R.color.blanco_KeyStore
                )
            )
            .drawWithContent {
                drawContent()
                drawLine(
                    color = Color(R.color.negro_KeyStore),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 5f
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 8.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Icon(
                imageVector = icono,
                contentDescription = "",
                tint = colorResource(id = R.color.azul_KeyStore)
            )

        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 8.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Text(text = textoItem, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DialogoCreacionCarpetaKeyStore(
    valor: String,
    alCambiarValor: (String) -> Unit,
    modifier: Modifier,
    alRechazar: () -> Unit,
    alConfirmar: () -> Unit,
    tituloDialogo: String,
) {
    AlertDialog(
        title = {
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = tituloDialogo,
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.negro_KeyStore),
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            InputDelineadoKeystore(
                valor = valor,
                alCambiarValor = alCambiarValor,
                modifier = modifier,
                etiqueta = { Text(text = "Nombre de la carpeta") },
                placeholder = { Text(text = "Un nombre...") },
                esContra = false
            )
        },
        onDismissRequest = {
            alRechazar()
        },
        confirmButton = {
            FilledTonalButton(
                onClick = alConfirmar,
                modifier = Modifier.width(140.dp),
                enabled = true,
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = colorResource(id = R.color.afirmativo_KeyStore),
                    contentColor = colorResource(id = R.color.blanco_KeyStore)
                ),
                elevation = ButtonDefaults.filledTonalButtonElevation()
            )
            {
                Text(
                    text = "Crear carpeta",
                )
            }
        },
        dismissButton = {
            FilledTonalButton(
                onClick = alRechazar,
                modifier = Modifier.width(110.dp),
                enabled = true,
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = colorResource(id = R.color.negativo_KeyStore),
                    contentColor = colorResource(id = R.color.blanco_KeyStore)
                ),
                elevation = ButtonDefaults.filledTonalButtonElevation()
            )
            {
                Text(
                    text = "Cancelar",
                )
            }
        },
        containerColor = colorResource(id = R.color.blanco_KeyStore)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniTarjetaFuncionDato(alClickar: () -> Unit, icono: ImageVector, textoTarjeta: String) {
    ElevatedCard(
        onClick = alClickar,
        modifier = Modifier.size(width = 120.dp, height = 80.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.blanco_KeyStore),
            contentColor = colorResource(id = R.color.negro_KeyStore)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(5.dp)
            ) {
                Icon(
                    imageVector = icono,
                    contentDescription = "compartir",
                    tint = colorResource(id = R.color.negro_KeyStore),
                    modifier = Modifier.size(30.dp)
                )
                Text(text = textoTarjeta)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoModalMasKeyStore(
    alCerrar: () -> Unit,
    estadoDialogo: SheetState,
    funcionTarjeta4: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = alCerrar,
        sheetState = estadoDialogo,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = colorResource(id = R.color.blanco_KeyStore)
    )
    {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(start = 10.dp, end = 10.dp),
            verticalArrangement = spacedBy(15.dp),
            content = {
                item {
                    Text(
                        text = "Más opciones...",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.drawWithContent {
                            drawContent()
                            drawLine(
                                color = Color(R.color.azul_KeyStore),
                                start = Offset(0f, size.height),
                                end = Offset(700f, size.height),
                                strokeWidth = 5f
                            )
                        }
                    )
                }
                item {
                    ElevatedCard(
                        modifier = Modifier.border(
                            1.dp,
                            colorResource(id = R.color.azul_KeyStore),
                            CardDefaults.elevatedShape
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.blanco_KeyStore),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        Text(
                            text = "Mover a carpeta",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        )
                    }
                }
                item {
                    ElevatedCard(
                        modifier = Modifier.border(
                            1.dp,
                            colorResource(id = R.color.azul_KeyStore),
                            CardDefaults.elevatedShape
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.blanco_KeyStore),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        Text(
                            text = "Eliminar",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            })
    }
}