package com.yarendemirkaya.detail.ui

data class UiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isFavorited: Boolean = false,
)