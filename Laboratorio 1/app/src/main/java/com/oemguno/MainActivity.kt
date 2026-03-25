package com.oemguno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pantalla()
        }
    }
}

@Composable
fun Pantalla() {

    val nombres = remember { mutableStateListOf<String>() }
    var texto by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        TextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Nombre") }
        )

        Button(onClick = {
            if (texto != "") {
                nombres.add(texto)
                texto = ""
            }
        }) {
            Text("Guardar")
        }

        Row {
            Text("Listado de nombres y posición en la lista")

            Button(onClick = {
                nombres.clear()
            }) {
                Text("Limpiar")
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .border(2.dp, Color.Blue)
                .padding(10.dp)
        ) {
            itemsIndexed(nombres) { index, nombre ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = nombre,
                        fontSize = 18.sp
                    )
                    Text(
                        text = (index + 1).toString(),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}