package com.example.stamurai_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IntegerRes

class MainActivity : AppCompatActivity() {

    companion object{
        var ratingRange: Pair<String,String> = Pair(String(),String())
        var rating:String = String()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
