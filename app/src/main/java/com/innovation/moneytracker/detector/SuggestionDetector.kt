package com.innovation.moneytracker.detector

import com.innovation.moneytracker.models.SMSMessage
import com.innovation.moneytracker.models.Suggestion

/**
 * Detects Suggestion by analysing the SMS Message
 */
abstract class SuggestionDetector {

    /**
     * Detect Suggestion by analyzing the SMS Message.
     */
    abstract fun detectSuggestions(smsMessage: SMSMessage): Suggestion?
}
