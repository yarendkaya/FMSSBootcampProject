package com.yarendemirkaya.home.ui

enum class SortType(val value: String, val type: SortCategory, val ascending: Boolean) {
    SORT_BY_PRICE_ASCENDING("Increasing by Price", SortCategory.PRICE, true),
    SORT_BY_PRICE_DESCENDING("Decreasing by Price", SortCategory.PRICE, false),
    SORT_BY_RATE_ASCENDING("Increasing by Rate", SortCategory.RATE, true),
    SORT_BY_RATE_DESCENDING("Decreasing by Rate", SortCategory.RATE, false)
}

enum class SortCategory {
    PRICE, RATE
}