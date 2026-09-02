package com.example.test // Paquete del proyecto: todas las clases de app/src/main/java/com/example/test viven acá.

import android.os.Bundle // Bundle: guarda el estado de la Activity (por ejemplo si rotás la pantalla). Lo recibe onCreate.
import androidx.activity.ComponentActivity // Clase base moderna de Activity, pensada para apps que usan Jetpack Compose.
import androidx.activity.compose.setContent // Función que conecta Compose con la Activity: le decís qué composables dibujar.
import androidx.activity.enableEdgeToEdge // Hace que la app dibuje detrás de la barra de estado/navegación (pantalla completa moderna).
import androidx.compose.foundation.layout.fillMaxSize // Modifier que hace que un elemento ocupe todo el ancho y alto disponible.
import androidx.compose.material3.Surface // Contenedor de Material3: pinta un fondo (color de superficie) detrás de su contenido.
import androidx.compose.ui.Modifier // Tipo genérico para "modificadores": tamaño, padding, color, etc. de un composable.
import com.example.test.ui.theme.TestTheme // El theme (colores, tipografía) que generó Android Studio con la plantilla del proyecto.

// MainActivity es el punto de entrada de la app: la primera pantalla que Android abre al tocar el ícono.
class MainActivity : ComponentActivity() { // Hereda de ComponentActivity para poder usar Compose.

    // onCreate se ejecuta una sola vez cuando el sistema crea la Activity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Siempre hay que llamar a la implementación de la clase padre primero.
        enableEdgeToEdge() // Activa el modo "pantalla completa" (el contenido puede llegar hasta los bordes).

        // setContent define, con Compose, todo lo que se va a mostrar en pantalla.
        setContent {
            TestTheme { // Envuelve todo con el theme del proyecto (colores/tipografía de Material3).
                // Surface pinta el fondo de toda la pantalla y ocupa el 100% del tamaño disponible.
                Surface(modifier = Modifier.fillMaxSize()) {
                    OnboardingScreen() // Acá se elige qué pantalla mostrar. Hoy muestra el Onboarding.
                    // Si quisieras ver Login o Register en el emulador en vez del Onboarding,
                    // cambiarías esta línea por LoginScreen() o RegisterScreen().
                }
            }
        }
    }
}
