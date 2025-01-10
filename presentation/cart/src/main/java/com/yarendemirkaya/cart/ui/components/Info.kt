package com.yarendemirkaya.cart.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Information(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Sepetin ödeme için hazır", color = Color.White) },
        text = { Text("Ödeme ekranına devam edilsin mi?", color = Color.White) },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonColors(
                    contentColor = Color(0xFFFFC107),
                    containerColor = Color.Black,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Black
                )
            ) {
                Text("Evet")
            }
        },
        dismissButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonColors(
                    contentColor = Color(0xFFFFC107),
                    containerColor = Color.Black,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Black)
            ) {
                Text("Hayır")
            }
        }, containerColor = Color(0xFF151515)
    )
}