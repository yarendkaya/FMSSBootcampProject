package com.yarendemirkaya.home.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.yarendemirkaya.home.R
import kotlinx.coroutines.delay

@Composable
fun AddCartButton(onCartClick: () -> Unit) {
    val isClicked = remember { mutableStateOf(false) }

    LaunchedEffect(isClicked.value) {
        if (isClicked.value) {
            delay(500)
            isClicked.value = false
        }
    }
    OutlinedButton(
        onClick = {
            isClicked.value = true
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
            text = "Add to Cart", fontFamily = FontFamily(
                Font(R.font.roboto_bold)
            )
        )
    }
}