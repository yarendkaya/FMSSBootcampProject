package com.yarendemirkaya.base.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddCartButton(onCartClick: () -> Unit) {
    val isClicked = remember { mutableStateOf(false) }
    OutlinedButton(
        onClick = {
            isClicked.value = !isClicked.value
            onCartClick()
        },
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isClicked.value) Color(0xFFFFA500) else Color(0xFF151515),
            contentColor = Color.White
        ),
        modifier = Modifier.padding(start = 40.dp),
        border = BorderStroke(2.dp, Color(0xFFFFA500))
    ) {
        Text(
            text = "Add to Cart"
        )
    }
}