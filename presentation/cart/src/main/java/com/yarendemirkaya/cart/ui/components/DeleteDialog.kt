package com.yarendemirkaya.cart.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DeleteDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Delete", color = Color.White) },
        text = { Text("Do you want to delete all items from cart?", color = Color.White) },
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
                Text("Yes")
            }
        },
        dismissButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonColors(
                    contentColor = Color(0xFFFFC107),
                    containerColor = Color.Black,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Black
                )
            ) {
                Text("No")
            }
        }, containerColor = Color(0xFF151515)
    )
}