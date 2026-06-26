package com.pdm0126.laboratorio1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm0126.laboratorio1.ui.theme.Laboratorio1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                        PantallaLaboratorio()
                    }
                }
            }
        }
    }
}

class Computadora(
    var marca: String,
    var ramGB: Int,
    var almacenamientoGB: Int,
    var sistemaOperativo: String,
    var estaEncendida: Boolean = false
) {
    fun encender(): String {
        estaEncendida = true
        return "La computadora se ha encendido."
    }

    fun apagar(): String {
        estaEncendida = false
        return "La computadora se ha apagado."
    }
}

class Calculadora(
    val marca: String,
    val aniosDeVida: Int,
    var precio: Double
) {
    fun sumar(a: Double, b: Double): Double = a + b
    fun restar(a: Double, b: Double): Double = a - b
    fun multiplicar(a: Double, b: Double): Double = a * b
    fun dividir(a: Double, b: Double): Double {
        if (b == 0.0) {
            return 0.0
        }
        return a / b
    }
}

class Estudiante(
    val nombre: String,
    val carnet: String,
    val asignatura: String
)

@Composable
fun PantallaLaboratorio() {
    Text(text = "--- EJERCICIO 1 ---")
    val miPC = Computadora("Asus", 16, 512, "Windows 11")
    val estadoEncendido = miPC.encender()
    miPC.ramGB = 32
    miPC.sistemaOperativo = "Ubuntu"

    Text(text = estadoEncendido)
    Text(text = "PC Actualizada: ${miPC.ramGB}GB RAM, SO: ${miPC.sistemaOperativo}")

    val programasInstalados = listOf("Notion 2026", "Facebook 2024", "Android Studio 2026", "Spotify 2023")
    Text(text = "Programas de este year:")
    programasInstalados.forEach { programa ->
        if (programa.contains("2026")) {
            Text(text = "- $programa")
        }
    }

    Text(text = "\n--- EJERCICIO 2 ---")
    val calc = Calculadora("Casio", 5, 25.0)
    val suma = calc.sumar(10.0, 5.0)
    val divisionErronea = calc.dividir(10.0, 0.0)
    Text(text = "Suma 10 + 5 = $suma")
    Text(text = "Division entre cero (Error evitado) = $divisionErronea")

    Text(text = "\n--- EJERCICIO 3 ---")
    val ciclo01 = listOf(
        Estudiante("Cristiano Ronaldo", "11224235", "Programacion de Dispositivos Moviles"),
        Estudiante("Ana Martinez", "22334455", "Analisis numerico 4"),
        Estudiante("Leo Messi", "55667788", "Programacion de Dispositivos Moviles"),
        Estudiante("Sofia Reyes", "99001122", "Analisis numerico 4")
    )

    Text(text = "Estudiantes en Dispositivos Moviles:")
    ciclo01.forEach { estudiante ->
        if (estudiante.asignatura == "Programacion de Dispositivos Moviles") {
            Text(text = "- ${estudiante.nombre} (${estudiante.carnet})")
        }
    }
}
