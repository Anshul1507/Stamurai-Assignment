package com.example.stamurai_assignment.rating

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stamurai_assignment.MainActivity
import com.example.stamurai_assignment.database.Rating
import com.example.stamurai_assignment.database.RatingDatabaseDao
import kotlinx.coroutines.*
import java.util.*


class RatingViewModel(
        val database: RatingDatabaseDao,
        application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun onStartTracking() {
        uiScope.launch {
            val newRating = Rating()
            newRating.totalRating = MainActivity.rating.toInt()
            insertAndUpdate(newRating)
        }
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }
    private suspend fun insertAndUpdate(rating: Rating){
        withContext(Dispatchers.IO){
            database.insert(rating)
            
            MainActivity.key = rating.ratingId.toString()
            snackBar()
            Log.i(">>",rating.toString())
        }
    }


    fun snackBar() {
        uiScope.launch {
            _showSnackbarEvent.value = true
        }
    }
}

