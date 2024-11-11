package com.example.restaurantproyectdam.ui.components.homecomponents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PermissionRequiredDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Permiso de Cámara Requerido") },
        text = {
            Text("Para acceder a esta opción, es necesario aceptar el permiso de cámara.")
        },
        confirmButton = {
            Button(onClick = onConfirmRequest) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Text("Cancelar")
            }
        }
    )
}