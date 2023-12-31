package com.innovation.moneytracker.detector


import com.innovation.moneytracker.models.Suggestion
import com.innovation.moneytracker.models.SMSMessage

/**
 * Suggestion Detector with the help of Regexp Parsing.
 */
class SuggestionDetectorImpl(private val regexHelper: RegexHelper) :
    SuggestionDetector() {

    /**
     * Check for smsMessage is of Transactional SMS and parse the Expense suggestion.
     */
    override fun detectSuggestions(smsMessage: SMSMessage): Suggestion? {
        val isExpense = regexHelper.isExpense(smsMessage.body)
        val spent = regexHelper.getAmountSpent(smsMessage.body)
        val paidToName = regexHelper.getPaidToName(smsMessage.body)

        if (isExpense && spent != null) {
            return Suggestion(
                id = generateUniqueId(smsMessage),
                amount = spent,
                paidTo = paidToName,
                time = smsMessage.time,
                referenceMessage = smsMessage.body,
                referenceMessageSender = smsMessage.address
            )
        }

        return null
    }

    private fun generateUniqueId(smsMessage: SMSMessage) =
        smsMessage.body.hashCode().toLong().plus(smsMessage.time)
}
