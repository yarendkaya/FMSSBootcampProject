package com.yarendemirkaya.base.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> Flow<T>.collectWithLifecycle(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    result: (T) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(this, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(state) {
            this@collectWithLifecycle.collect { effect ->
                result(effect)
            }
        }
    }
}