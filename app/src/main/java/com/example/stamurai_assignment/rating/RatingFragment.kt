package com.example.stamurai_assignment.rating

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stamurai_assignment.MainActivity
import com.example.stamurai_assignment.R
import com.example.stamurai_assignment.database.Rating
import com.example.stamurai_assignment.database.RatingDatabase
import com.example.stamurai_assignment.database.RatingDatabaseDao
import com.example.stamurai_assignment.databinding.FragmentRatingBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_rating.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Created on 05-04-2020
 * By Anshul1507
 */
class RatingFragment : Fragment(), SeekBar.OnSeekBarChangeListener{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        // Inflate the layout for this fragment
        val binding: FragmentRatingBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_rating,container,false
        )
        //default to 0th index rating
        MainActivity.rating = MainActivity.ratingRange.first

        val application = requireNotNull(this.activity).application
        binding.sliderRating.max = MainActivity.ratingRange.second.toInt() - MainActivity.ratingRange.first.toInt()


        binding.sliderRating.setOnSeekBarChangeListener(this)


        val dataSource = RatingDatabase.getInstance(application).ratingDatabaseDao
        val viewModelFactory = RatingViewModelFactory(dataSource,application)

        val viewModel =
            ViewModelProvider(
                this,viewModelFactory
            ).get(RatingViewModel::class.java)

        binding.ratingViewModel = viewModel

        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if(it == true){
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.saved_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.sliderRating.progress = 0
                viewModel.doneShowingSnackbar()
            }
        })


        binding.labelRating.text = "Give Rating b/w ${MainActivity.ratingRange.first} - ${MainActivity.ratingRange.second}"
        binding.textRating.text = "Rating is: ${MainActivity.ratingRange.first}"

        return binding.root
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        var x = (p1 + MainActivity.ratingRange.first.toInt())
        text_rating.text = "Rating is : $x"
        MainActivity.rating = (p1+ MainActivity.ratingRange.first.toInt()).toString()

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }


}

