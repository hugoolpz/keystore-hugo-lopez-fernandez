package com.example.keystore_hugolopezfernandez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.keystore_hugolopezfernandez.navegacion.SistemaNav
import com.example.keystore_hugolopezfernandez.ui.theme.KeyStoreHugoLopezFernandezTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeyStoreHugoLopezFernandezTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SistemaNav()
                }
            }
        }
    }
}