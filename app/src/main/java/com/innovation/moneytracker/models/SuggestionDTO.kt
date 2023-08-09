package com.innovation.moneytracker.models

class SuggestionDTO(
    val id: Long,
    val amount: Double,
    val paidTo: String?,
    val time: Long,
    val referenceMessage: String,
    val referenceMessageSender: String
)
