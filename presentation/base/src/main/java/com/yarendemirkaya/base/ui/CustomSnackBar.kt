package com.yarendemirkaya.base.ui

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CustomSnackBar(text: String) {
    SnackbarHost(
        hostState = SnackbarHostState()
    ) { snackbarData ->
        Snackbar(
            snackbarData = snackbarData,
            actionColor = Color.Red,
            containerColor = Color.Black,
            contentColor = Color.White
        )
    }
}