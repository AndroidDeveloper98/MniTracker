package com.innovation.moneytracker

import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.innovation.moneytracker.databinding.ActivityMainBinding
import com.innovation.moneytracker.detector.SuggestionDetector
import com.innovation.moneytracker.mappers.SMSMessageDataMapper
import com.innovation.moneytracker.models.Suggestion
import com.innovation.moneytracker.models.SuggestionListState
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    @Inject
    lateinit var suggestionDetector: SuggestionDetector
    @Inject
    lateinit var dataMapper: SMSMessageDataMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val smsReadAPI = SMSReadAPI(contentResolver)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR, 1)
        val data = smsReadAPI.getAllSms(calendar.timeInMillis).mapNotNull { smsMessageDTO ->
            suggestionDetector.detectSuggestions(
                dataMapper.mapToSMSMessage(
                    smsMessageDTO
                )
            )
        }
        val dateBiasData = getSuggestionsByDate(data)
        val notesListAdapter = DemoListAdapter(this, dateBiasData)
        binding.rvNotesList.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = notesListAdapter
        }

    }


    private fun getSuggestionsByDate(expenses: List<Suggestion>): Map<String, List<SuggestionListState.Item>> =
        expenses
            .groupBy { expense ->
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = expense.time
                calendar.get(Calendar.DAY_OF_YEAR)
            }.toSortedMap { day1, day2 ->
                performDescendingCompare(day1, day2)
            }
            .mapValues { mapEntry ->
                mapEntry.value.map { suggestion ->
                    SuggestionListState.Item(
                        id = suggestion.id,
                        amount = NumberFormat.getCurrencyInstance().format(suggestion.amount),
                        message = suggestion.referenceMessage,
                        paidTo = suggestion.paidTo?:"-----"
                    )
                }
            }.mapKeys { mapEntry ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.DAY_OF_YEAR, mapEntry.key)
                SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(calendar.timeInMillis)
            }

    private fun performDescendingCompare(day1: Int, day2: Int) = when {
        day1 < day2 -> 1
        day2 < day1 -> -1
        else -> 0
    }

}