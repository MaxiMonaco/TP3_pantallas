package com.example.test // Mismo paquete que el resto de la app.

import androidx.compose.foundation.layout.Column // Apila los elementos verticalmente.
import androidx.compose.foundation.layout.Spacer // Espacio vacío entre elementos.
import androidx.compose.foundation.layout.fillMaxSize // Modifier: ocupa todo el ancho y alto disponible.
import androidx.compose.foundation.layout.fillMaxWidth // Modifier: ocupa todo el ancho disponible.
import androidx.compose.foundation.layout.height // Modifier: fija una altura (para los Spacer).
import androidx.compose.foundation.layout.padding // Modifier: agrega margen interno.
import androidx.compose.material3.MaterialTheme // Da acceso a la tipografía del theme.
import androidx.compose.material3.Text // Composable de texto.
import androidx.compose.runtime.Composable // Marca la función como composable.
import androidx.compose.ui.Alignment // Constantes de alineación.
import androidx.compose.ui.Modifier // Tipo genérico para modificadores.
import androidx.compose.ui.graphics.Color // Representa un color.
import androidx.compose.ui.res.stringResource // Carga textos desde strings.xml.
import androidx.compose.ui.text.font.FontWeight // Grosor de la tipografía.
import androidx.compose.ui.text.style.TextAlign // Alineación del texto.
import androidx.compose.ui.tooling.preview.Preview // Habilita el panel de Preview en Android Studio.
import androidx.compose.ui.unit.dp // Unidad de medida independiente de la densidad.
import com.example.test.ui.theme.TestTheme // Theme del proyecto, usado en el Preview.

/**
 * Replica SOLO visual de la pantalla de Register del Figma del challenge.
 * No tiene navegación ni lógica: no valida, no crea cuentas, no guarda nada.
 */
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier, // Modifier opcional, para poder personalizar desde afuera.
) {
    Column( // Apila todo el contenido: título, subtítulo, campos, botón, etc.
        modifier = modifier
            .fillMaxSize() // Ocupa toda la pantalla.
            .padding(horizontal = 24.dp), // Margen de 24dp a los costados.
        horizontalAlignment = Alignment.CenterHorizontally, // Centra todo horizontalmente.
    ) {
        Spacer(Modifier.height(64.dp)) // Espacio arriba de todo.

        Text(
            text = stringResource(id = R.string.register_title), // "Create Account".
            style = MaterialTheme.typography.headlineMedium, // Tipografía grande, para títulos.
            fontWeight = FontWeight.Bold, // Negrita.
            color = AuthPrimaryBlue, // Azul principal (definido en AuthComponents.kt).
            textAlign = TextAlign.Center, // Centrado.
        )

        Spacer(Modifier.height(12.dp)) // Separación entre título y subtítulo.

        Text(
            text = stringResource(id = R.string.register_subtitle), // "Create an account so you can explore all the existing jobs".
            fontWeight = FontWeight.Bold, // Negrita, como en el diseño.
            color = Color.Black, // Negro puro.
            textAlign = TextAlign.Center, // Centrado (ocupa 2 líneas).
        )

        Spacer(Modifier.height(32.dp)) // Separación antes de los campos de texto.

        // Los 3 campos usan el mismo componente reutilizable AuthTextField (de AuthComponents.kt).
        AuthTextField(
            placeholder = stringResource(id = R.string.field_email), // "Email".
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(16.dp)) // Separación entre campos.

        AuthTextField(
            placeholder = stringResource(id = R.string.field_password), // "Password".
            isPassword = true, // Oculta el texto tipeado.
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(16.dp)) // Separación entre campos.

        AuthTextField(
            placeholder = stringResource(id = R.string.field_confirm_password), // "Confirm Password".
            isPassword = true, // También oculta el texto.
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(24.dp)) // Separación antes del botón principal.

        // AuthPrimaryButton es el botón azul reutilizable, definido en AuthComponents.kt.
        AuthPrimaryButton(
            text = stringResource(id = R.string.sign_up), // "Sign up".
            modifier = Modifier.fillMaxWidth(), // Ocupa todo el ancho.
        )

        Spacer(Modifier.height(24.dp)) // Separación antes de "Already have an account".

        Text(
            text = stringResource(id = R.string.already_have_account), // "Already have an account".
            fontWeight = FontWeight.Medium, // Grosor intermedio.
            color = Color.Black,
        )

        Spacer(Modifier.height(24.dp)) // Separación antes de "Or continue with".

        Text(
            text = stringResource(id = R.string.or_continue_with), // "Or continue with".
            color = AuthPrimaryBlue, // Azul, igual que en el Figma.
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(Modifier.height(16.dp)) // Separación antes de los íconos sociales.

        SocialLoginRow() // Composable de AuthComponents.kt: dibuja los 3 íconos (Google, Facebook, Apple).

        Spacer(Modifier.height(32.dp)) // Espacio final, antes del borde inferior.
    }
}

// @Preview permite ver esta pantalla en Android Studio sin correr la app en un emulador.
@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() { // Función solo para la vista previa.
    TestTheme { // Aplica el theme del proyecto.
        RegisterScreen() // Dibuja la pantalla real.
    }
}
