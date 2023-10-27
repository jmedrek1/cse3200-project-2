package com.example.criminalintent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
private const val TAG = "CrimeListViewModel"
class CrimeListViewModel : ViewModel() {
    val crimes = mutableListOf<Crime>()
    init {
        Log.d(TAG, "about to initialize our crime data")
        viewModelScope.launch {
            Log.d(TAG, "coroutine launched. expect delay")
            crimes += loadCrimes()
            Log.d(TAG, "Crime data should be finished")
        }
    }

    suspend fun loadCrimes() : List<Crime> {
//        delay(5000)
        var result = mutableListOf<Crime>()
        for (i in 0 until 100) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title = "Crime #$i",
                date = Date(),
                isSolved = i % 2 == 0
            )
            result.add(crime)
        }
        return result
    }
}