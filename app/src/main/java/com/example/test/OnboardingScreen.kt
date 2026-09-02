package com.example.test // Mismo paquete que el resto de la app.

import androidx.compose.foundation.Image // Composable para mostrar una imagen (la ilustración del onboarding).
import androidx.compose.foundation.layout.Arrangement // Controla cómo se separan/alinean los elementos en Row/Column.
import androidx.compose.foundation.layout.Column // Contenedor que apila sus hijos verticalmente, uno debajo del otro.
import androidx.compose.foundation.layout.Row // Contenedor que pone sus hijos en fila, uno al lado del otro.
import androidx.compose.foundation.layout.Spacer // Espacio vacío, se usa para separar elementos sin agregar contenido.
import androidx.compose.foundation.layout.fillMaxSize // Modifier: ocupa todo el ancho y alto disponible.
import androidx.compose.foundation.layout.fillMaxWidth // Modifier: ocupa todo el ancho disponible (alto libre).
import androidx.compose.foundation.layout.height // Modifier: fija una altura específica (lo usamos en los Spacer).
import androidx.compose.foundation.layout.padding // Modifier: agrega margen interno alrededor de un elemento.
import androidx.compose.foundation.layout.width // Modifier: fija un ancho específico (lo usamos en un Spacer horizontal).
import androidx.compose.material3.Button // Botón relleno de Material3 (el botón "Login").
import androidx.compose.material3.ButtonDefaults // Valores por defecto de Button, para cambiar su color.
import androidx.compose.material3.MaterialTheme // Da acceso a la tipografía y colores del theme del proyecto.
import androidx.compose.material3.Text // Composable para mostrar texto.
import androidx.compose.material3.TextButton // Botón "de texto", sin fondo (el botón "Register").
import androidx.compose.runtime.Composable // Anotación que marca una función como dibujable por Compose.
import androidx.compose.ui.Alignment // Constantes para alinear elementos (centrado, etc).
import androidx.compose.ui.Modifier // Tipo genérico para modificadores.
import androidx.compose.ui.graphics.Color // Representa un color.
import androidx.compose.ui.res.painterResource // Carga una imagen/drawable para poder dibujarla.
import androidx.compose.ui.res.stringResource // Carga un texto desde res/values/strings.xml (para poder traducir la app fácil).
import androidx.compose.ui.text.font.FontWeight // Grosor de la tipografía.
import androidx.compose.ui.text.style.TextAlign // Alineación del texto (centrado, izquierda, etc).
import androidx.compose.ui.tooling.preview.Preview // Anotación que permite ver el composable en el panel de Preview sin correr la app.
import androidx.compose.ui.unit.dp // Unidad de medida independiente de la densidad de pantalla.
import com.example.test.ui.theme.TestTheme // El theme del proyecto (colores/tipografía), lo usamos en el Preview.

// AuthPrimaryBlue viene de AuthComponents.kt: es el mismo azul que se usa en
// Login y Register, medido del Figma del challenge.
// "private" = esta variable solo se puede usar dentro de este archivo.
private val OnboardingTextGray = Color(0xFF6B7280) // Color gris para el subtítulo, sacado del diseño original.

/**
 * Replica SOLO visual de la pantalla de onboarding. No tiene navegación ni
 * lógica: los botones no hacen nada todavía.
 */
@Composable // Convierte esta función en un composable, usable como un widget dentro de otras pantallas.
fun OnboardingScreen(
    modifier: Modifier = Modifier, // Modifier opcional: quien use OnboardingScreen puede pasarle uno propio.
) {
    Column( // Apila todo el contenido de la pantalla verticalmente.
        modifier = modifier
            .fillMaxSize() // La columna ocupa toda la pantalla.
            .padding(horizontal = 24.dp), // Deja 24dp de margen a los costados (izquierda y derecha).
        horizontalAlignment = Alignment.CenterHorizontally, // Centra todos los hijos horizontalmente.
    ) {
        Spacer(Modifier.height(20.dp)) // Espacio vacío de 20dp antes de la imagen (separación del borde superior).

        Image(
            painter = painterResource(id = R.drawable.onboarding_illustration_photo), // La ilustración de la persona con la laptop.
            contentDescription = null, // null porque es decorativa, no necesita descripción para accesibilidad.
            modifier = Modifier.fillMaxWidth(), // La imagen ocupa todo el ancho disponible (se agranda/achica según el ancho de pantalla).
        )

        Spacer(Modifier.weight(1f)) // Spacer "elástico": empuja el resto del contenido hacia abajo, ocupando el espacio libre.

        Text(
            text = stringResource(id = R.string.onboarding_title), // El texto "Discover Your Dream Job here", desde strings.xml.
            style = MaterialTheme.typography.headlineSmall, // Usa el estilo de tipografía "headlineSmall" definido en el theme.
            fontWeight = FontWeight.Bold, // Texto en negrita.
            color = AuthPrimaryBlue, // Color azul principal (compartido con Login/Register).
            textAlign = TextAlign.Center, // El texto se centra (importante porque ocupa 2 líneas).
        )

        Spacer(Modifier.height(12.dp)) // Separación de 12dp entre el título y el subtítulo.

        Text(
            text = stringResource(id = R.string.onboarding_subtitle), // El texto explicativo debajo del título.
            style = MaterialTheme.typography.bodyMedium, // Estilo de texto más chico, para texto de cuerpo.
            color = OnboardingTextGray, // Gris, para que no compita visualmente con el título.
            textAlign = TextAlign.Center, // Centrado.
        )

        Spacer(Modifier.height(28.dp)) // Separación antes de los botones.

        Row( // Fila con los dos botones (Login y Register) uno al lado del otro.
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center, // Centra los botones horizontalmente dentro de la fila.
            verticalAlignment = Alignment.CenterVertically, // Alinea verticalmente al centro (por si tuvieran distinta altura).
        ) {
            Button( // Botón relleno "Login".
                onClick = { /* solo visual, sin lógica */ }, // No navega a ningún lado, es solo visual.
                colors = ButtonDefaults.buttonColors(
                    containerColor = AuthPrimaryBlue, // Fondo azul.
                ),
                shape = MaterialTheme.shapes.medium, // Usa la forma "medium" (esquinas redondeadas) definida en el theme.
            ) {
                Text(
                    text = stringResource(id = R.string.login), // El texto "Login".
                    fontWeight = FontWeight.SemiBold, // Un poco menos grueso que "Bold".
                )
            }

            Spacer(Modifier.width(24.dp)) // Separación horizontal de 24dp entre los dos botones.

            TextButton(onClick = { /* solo visual, sin lógica */ }) { // Botón "Register" sin fondo, solo texto.
                Text(
                    text = stringResource(id = R.string.register), // El texto "Register".
                    color = AuthPrimaryBlue, // Mismo azul que el resto, para que se note que es "clickeable".
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        Spacer(Modifier.height(32.dp)) // Espacio final, antes del borde inferior de la pantalla.
    }
}

// @Preview le dice a Android Studio "mostrame este composable en el panel de Preview".
@Preview(showBackground = true) // showBackground = true agrega un fondo blanco, para que se vea como una pantalla real.
@Composable
private fun OnboardingScreenPreview() { // Función aparte, solo para la vista previa (no se usa en la app real).
    TestTheme { // Envuelve con el theme, así los colores/tipografía se ven igual que en la app.
        OnboardingScreen() // Dibuja la pantalla real, sin pasarle ningún parámetro (usa los valores por defecto).
    }
}
