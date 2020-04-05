/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

            insertAndUpdate(newRating)
        }
    }

    private suspend fun insertAndUpdate(rating: Rating){
        withContext(Dispatchers.IO){
            database.insert(rating)
            rating.totalRating = MainActivity.rating.toInt()
            database.update(rating)
            Log.i(">>",rating.toString())
        }
    }

}

