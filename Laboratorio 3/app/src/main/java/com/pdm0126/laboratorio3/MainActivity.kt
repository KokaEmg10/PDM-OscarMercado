package com.pdm0126.laboratorio3

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pdm0126.laboratorio3.ui.theme.Laboratorio3Theme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio3Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = DestinoHome) {
                    composable<DestinoHome> {
                        PantallaHome(navController)
                    }
                    composable<DestinoLista> {
                        PantallaListaNombres(navController)
                    }
                    composable<DestinoSensores> {
                        PantallaSensores(navController)
                    }
                }
            }
        }
    }
}

@Serializable
object DestinoHome

@Serializable
object DestinoLista

@Serializable
object DestinoSensores

class Estudiante(
    val nombre: String,
    val carnet: String,
    val asignatura: String
)

@Composable
fun useSensor(sensorType: Int): List<Float> {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val sensor = sensorManager.getDefaultSensor(sensorType) ?: return emptyList()
    var sensorValues by remember { mutableStateOf(listOf(0f, 0f, 0f)) }

    DisposableEffect(sensorType) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.values?.let {
                    sensorValues = it.toList()
                }
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    return sensorValues
}

@Composable
fun PantallaHome(navController: NavController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Laboratorio 3: Menú Principal",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate(DestinoLista) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ver Lista de Nombres")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate(DestinoSensores) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ver Información de Sensores")
            }
        }
    }
}

@Composable
fun PantallaListaNombres(navController: NavController) {
    val ciclo01 = listOf(
        Estudiante("Cristiano Ronaldo", "11224235", "Programación de Dispositivos Móviles"),
        Estudiante("Jose Manuel", "22334455", "Análisis numérico 4"),
        Estudiante("Luis Torres", "55667788", "Programación de Dispositivos Móviles"),
        Estudiante("Leo Messi", "99001122", "Análisis numérico 4")
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Estudiantes en Dispositivos Móviles:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            ciclo01.forEach { estudiante ->
                if (estudiante.asignatura == "Programación de Dispositivos Móviles") {
                    Text(
                        text = "- ${estudiante.nombre} (${estudiante.carnet})",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Volver al Menú")
            }
        }
    }
}

@Composable
fun PantallaSensores(navController: NavController) {
    val lightValues = useSensor(Sensor.TYPE_LIGHT)
    val intensidadLuz = if (lightValues.isNotEmpty()) lightValues[0] else 0f

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Información del Sensor de Luz",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Intensidad actual:",
                fontSize = 18.sp
            )
            Text(
                text = "$intensidadLuz lx",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(48.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Volver al Menú")
            }
        }
    }
}