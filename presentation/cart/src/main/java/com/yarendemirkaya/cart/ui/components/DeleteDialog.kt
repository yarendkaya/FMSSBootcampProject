package com.yarendemirkaya.cart.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.yarendemirkaya.cart.R

@Composable
fun DeleteDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(R.string.delete_title), color = Color.White) },
        text = { Text(text = stringResource(R.string.delete_question), color = Color.White) },
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
                Text(text = stringResource(R.string.btn_yes))
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
                Text(text = stringResource(R.string.btn_no))
            }
        }, containerColor = Color(0xFF151515)
    )
}