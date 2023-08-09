package com.innovation.moneytracker.models

data class Suggestion(
    val id: Long,
    val amount: Double,
    val paidTo: String?,
    val time: Long,
    val referenceMessage: String,
    val referenceMessageSender: String
)
