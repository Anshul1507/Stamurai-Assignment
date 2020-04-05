package com.example.stamurai_assignment.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

import com.example.stamurai_assignment.database.Rating
import com.example.stamurai_assignment.database.RatingDatabaseDao
import com.example.stamurai_assignment.formatNights
import kotlinx.coroutines.*

/**
 * Created on 05-04-2020
 * By Anshul1507
 */

class PastDataViewModel(
        val database: RatingDatabaseDao,
        application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val ratings = database.getAllRatings()

    val clearButtonVisible = Transformations.map(ratings){
        it?.isNotEmpty()
    }
    val nightsString = Transformations.map(ratings){
        formatNights(it,application.resources)
    }


    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun onClear() {
        uiScope.launch {
            clear()
            _showSnackbarEvent.value = true
        }
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

}

