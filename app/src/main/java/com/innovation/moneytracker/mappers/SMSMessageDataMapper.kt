package com.innovation.moneytracker.mappers

import com.innovation.moneytracker.SMSMessageDTO
import com.innovation.moneytracker.models.SMSMessage

class SMSMessageDataMapper {
    fun mapToSMSMessage(smsMessageDTO: SMSMessageDTO) = SMSMessage(
        address = smsMessageDTO.address,
        body = smsMessageDTO.body,
        time = smsMessageDTO.time,
    )
}
