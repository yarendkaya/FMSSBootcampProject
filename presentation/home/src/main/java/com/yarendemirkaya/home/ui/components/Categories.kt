package com.yarendemirkaya.home.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Categories(categories: List<String>) {
    val selectedCategory =
        remember { mutableStateOf(categories.firstOrNull()) } //defensive programming
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            val isSelected = category == selectedCategory.value
            OutlinedButton(
                onClick = { selectedCategory.value = category },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isSelected) Color(0xFFFFA500) else Color(0xFF151515),
                    contentColor = if (isSelected) Color.White else Color.Gray,), shape = RoundedCornerShape(20.dp),
                border = BorderStroke(2.dp, Color(0xFFFFA500))
            ) {
                Text(text = category)
            }
        }
    }
}
// secili olan all gelsin
// baska butona tıklandığında butpnun içi turuncu yazısı beyaz olsun.
