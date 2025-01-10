package com.yarendemirkaya.home.ui

enum class SortType(val value: String, val type: SortCategory, val ascending: Boolean) {
    SORT_BY_PRICE_ASCENDING("Fiyata Göre Artan", SortCategory.PRICE, true),
    SORT_BY_PRICE_DESCENDING("Fiyata Göre Azalan", SortCategory.PRICE, false),
    SORT_BY_RATE_ASCENDING("Puana Göre Artan", SortCategory.RATE, true),
    SORT_BY_RATE_DESCENDING("Puana Göre Azalan", SortCategory.RATE, false)
}

enum class SortCategory {
    PRICE, RATE
}