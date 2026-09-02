package com.example.test // Mismo paquete que el resto de las pantallas, así se pueden usar sin importar nada extra.

import androidx.compose.foundation.Image // Composable para mostrar una imagen (la usamos para los íconos de Google/Facebook/Apple).
import androidx.compose.foundation.layout.Arrangement // Controla cómo se separan/alinean los elementos dentro de una Row o Column.
import androidx.compose.foundation.layout.Box // Contenedor que apila elementos uno encima del otro (lo usamos para centrar el ícono).
import androidx.compose.foundation.layout.Row // Contenedor que ordena sus elementos horizontalmente, uno al lado del otro.
import androidx.compose.foundation.layout.size // Modifier para fijar ancho y alto iguales (un cuadrado/círculo).
import androidx.compose.foundation.shape.CircleShape // Una forma circular, la usamos para los botones sociales.
import androidx.compose.foundation.shape.RoundedCornerShape // Forma con esquinas redondeadas, para inputs y botones.
import androidx.compose.material3.Button // Botón relleno de Material3 (fondo de color sólido).
import androidx.compose.material3.ButtonDefaults // Valores por defecto de Button, los usamos para cambiar el color de fondo.
import androidx.compose.material3.OutlinedTextField // Campo de texto de Material3 con un borde alrededor.
import androidx.compose.material3.OutlinedTextFieldDefaults // Valores por defecto de OutlinedTextField (colores de fondo/borde).
import androidx.compose.material3.Surface // Contenedor con color de fondo y forma; lo usamos para el círculo de cada ícono social.
import androidx.compose.material3.Text // Composable para mostrar texto.
import androidx.compose.runtime.Composable // Anotación que marca una función como "dibujable" por Compose.
import androidx.compose.runtime.getValue // Permite leer un valor de estado con la sintaxis "by" (value en vez de value.value).
import androidx.compose.runtime.mutableStateOf // Crea un valor observable: cuando cambia, Compose vuelve a dibujar lo que lo usa.
import androidx.compose.runtime.remember // Hace que un valor sobreviva a los redibujados (no se resetea en cada recomposición).
import androidx.compose.runtime.setValue // Permite escribir un valor de estado con la sintaxis "by" (value = ... en vez de value.value = ...).
import androidx.compose.ui.Modifier // Tipo genérico para modificadores (tamaño, padding, color, click, etc).
import androidx.compose.ui.graphics.Color // Representa un color (lo definimos con un valor hexadecimal ARGB).
import androidx.compose.ui.res.painterResource // Carga un drawable (imagen/ícono) de res/drawable para poder dibujarlo.
import androidx.compose.ui.text.font.FontWeight // Grosor de la tipografía (Bold, SemiBold, etc).
import androidx.compose.ui.unit.dp // Unidad de medida independiente de la densidad de pantalla (density-independent pixels).

/**
 * Colores comunes a las 3 pantallas (Onboarding, Login, Register), tomados
 * del Figma del challenge. Solo se usan acá, no tocan el theme del proyecto.
 */
// "val" a nivel de archivo = una constante global que cualquier archivo del paquete puede usar sin importar nada.
val AuthPrimaryBlue = Color(0xFF1F41BC) // Azul principal: título, botones y links. 0xFF = opacidad 100%, 1F41BC = el color en hex.
val AuthInputBackground = Color(0xFFF1F3FF) // Lavanda clarito: fondo de los campos de texto (Email, Password, etc).
val AuthIconBackground = Color(0xFFECECEC) // Gris clarito: fondo circular de los botones de Google/Facebook/Apple.

/**
 * Campo de texto con el estilo del Figma: fondo lavanda claro, esquinas
 * redondeadas y borde azul cuando está enfocado. Solo visual: guarda lo que
 * se tipea en memoria, no valida ni envía nada a ningún lado.
 */
@Composable // Marca esta función como un composable: se puede usar dentro de otras pantallas como si fuera un widget.
fun AuthTextField(
    placeholder: String, // Texto gris que se ve cuando el campo está vacío (ej: "Email").
    modifier: Modifier = Modifier, // Permite que quien use este componente le agregue tamaño/padding desde afuera.
    isPassword: Boolean = false, // Si es true, oculta lo que se tipea con puntitos (para contraseñas).
) {
    // "var ... by remember { mutableStateOf(...) }" = estado local que Compose recuerda entre redibujados.
    var value by remember { mutableStateOf("") } // Acá se guarda, en memoria, lo que el usuario va tipeando.
    OutlinedTextField(
        value = value, // Lo que se muestra actualmente adentro del campo.
        onValueChange = { value = it }, // Cada vez que el usuario tipea, actualiza "value" con el nuevo texto.
        placeholder = { Text(placeholder) }, // El texto gris de ejemplo, mientras el campo está vacío.
        singleLine = true, // El campo no permite saltos de línea, todo el texto va en una sola fila.
        visualTransformation = if (isPassword) {
            // Si es un campo de contraseña, reemplaza cada caracter tipeado por un punto (•).
            androidx.compose.ui.text.input.PasswordVisualTransformation()
        } else {
            // Si no es contraseña, muestra el texto tal cual se tipea.
            androidx.compose.ui.text.input.VisualTransformation.None
        },
        shape = RoundedCornerShape(12.dp), // Esquinas redondeadas de 12dp, igual que en el diseño de Figma.
        colors = OutlinedTextFieldDefaults.colors( // Acá se pisan los colores por defecto de Material3.
            focusedContainerColor = AuthInputBackground, // Color de fondo cuando el campo está enfocado (tocado).
            unfocusedContainerColor = AuthInputBackground, // Color de fondo cuando el campo NO está enfocado.
            focusedBorderColor = AuthPrimaryBlue, // Color del borde cuando está enfocado (el azul del diseño).
            unfocusedBorderColor = Color.Transparent, // Sin borde visible cuando no está enfocado.
        ),
        modifier = modifier, // Acá se aplica el modifier que recibió la función (por ejemplo, fillMaxWidth).
    )
}

/** Botón de acción principal (Sign in / Sign up), relleno en azul. */
@Composable
fun AuthPrimaryButton(
    text: String, // El texto que va a mostrar el botón (ej: "Sign in" o "Sign up").
    modifier: Modifier = Modifier, // Permite ajustar tamaño/posición desde donde se use el botón.
) {
    Button(
        onClick = { /* solo visual, sin lógica */ }, // Qué pasa al tocar el botón: nada, es solo visual.
        colors = ButtonDefaults.buttonColors(containerColor = AuthPrimaryBlue), // Fondo azul del botón.
        shape = RoundedCornerShape(12.dp), // Esquinas redondeadas, igual que los inputs.
        modifier = modifier, // Modifier recibido desde afuera (ej: fillMaxWidth para que ocupe todo el ancho).
    ) {
        Text(text, fontWeight = FontWeight.Bold) // El texto del botón, en negrita.
    }
}

/** Fila con los 3 botones circulares de login social (Google, Facebook, Apple). */
@Composable
fun SocialLoginRow(modifier: Modifier = Modifier) {
    Row( // Row = pone sus 3 hijos en fila, uno al lado del otro.
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Deja 16dp de separación entre cada ícono.
    ) {
        // Un SocialIconButton por cada red social, cada uno con su propio ícono.
        SocialIconButton(iconRes = R.drawable.ic_google) // R.drawable.ic_google = el ícono de res/drawable/ic_google.xml.
        SocialIconButton(iconRes = R.drawable.ic_facebook)
        SocialIconButton(iconRes = R.drawable.ic_apple)
    }
}

// "private" = solo se puede usar dentro de este archivo (SocialLoginRow es la única que lo llama).
@Composable
private fun SocialIconButton(iconRes: Int) { // iconRes es el ID del drawable (ej: R.drawable.ic_google).
    Surface( // Superficie circular que sirve de "botón" para el ícono.
        shape = CircleShape, // Forma circular.
        color = AuthIconBackground, // Fondo gris clarito.
        modifier = Modifier.size(48.dp), // El círculo mide 48dp de diámetro.
        onClick = { /* solo visual, sin lógica */ }, // No hace nada al tocarlo, es solo visual.
    ) {
        Box( // Box permite centrar el ícono adentro del círculo.
            contentAlignment = androidx.compose.ui.Alignment.Center, // Centra el contenido (el ícono) en el medio del Box.
            modifier = Modifier.size(48.dp), // Mismo tamaño que el círculo de afuera.
        ) {
            Image(
                painter = painterResource(id = iconRes), // Carga el drawable que le pasaron como parámetro.
                contentDescription = null, // null porque el ícono es decorativo (no aporta info nueva para accesibilidad).
                modifier = Modifier.size(20.dp), // El ícono en sí mide 20dp (más chico que el círculo de fondo).
            )
        }
    }
}
