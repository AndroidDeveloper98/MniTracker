package com.innovation.moneytracker

data class SMSMessageDTO(
    val address: String,
    val body: String,
    val time: Long
)
