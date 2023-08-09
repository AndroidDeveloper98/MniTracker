package com.innovation

import com.innovation.moneytracker.detector.SuggestionDetectorImpl
import com.innovation.moneytracker.detector.RegexHelper
import com.innovation.moneytracker.detector.SuggestionDetector
import com.innovation.moneytracker.mappers.SMSMessageDataMapper
import com.innovation.moneytracker.mappers.SuggestionDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideSuggestionParserHelper(): RegexHelper {
        return RegexHelper()
    }

    @Provides
    fun provideSuggestionDataMapper(): SuggestionDataMapper {
        return SuggestionDataMapper()
    }

    @Provides
    fun provideSMSDataMapper(): SMSMessageDataMapper {
        return SMSMessageDataMapper()
    }

    @Provides
    fun provideSuggestionDetector(regexHelper: RegexHelper): SuggestionDetector {
        return SuggestionDetectorImpl(regexHelper)
    }

}
