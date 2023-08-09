package com.innovation.moneytracker.models

data class SMSMessage(
    val address: String,
    val body: String,
    val time: Long
)
