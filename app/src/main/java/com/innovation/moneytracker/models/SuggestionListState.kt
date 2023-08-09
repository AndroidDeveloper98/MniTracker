package com.innovation.moneytracker.models

data class SuggestionListState(
    val dateSuggestionsMap: Map<String, List<Item>> = emptyMap()
) {
    data class Item(
        val id: Long,
        val amount: String,
        val message: String,
        val paidTo: String
    )
}
