package com.innovation.moneytracker.mappers

import com.innovation.moneytracker.models.Suggestion
import com.innovation.moneytracker.models.SuggestionDTO


class SuggestionDataMapper {

    fun mapToSuggestion(suggestion: SuggestionDTO) = Suggestion(
        id = suggestion.id,
        amount = suggestion.amount,
        paidTo = suggestion.paidTo,
        time = suggestion.time,
        referenceMessage = suggestion.referenceMessage,
        referenceMessageSender = suggestion.referenceMessageSender
    )
}
