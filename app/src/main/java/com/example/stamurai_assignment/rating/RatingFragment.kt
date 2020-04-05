package com.example.stamurai_assignment.rating

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.stamurai_assignment.R
import com.example.stamurai_assignment.databinding.FragmentRatingBinding


/**
 * A simple [Fragment] subclass.
 */
class RatingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentRatingBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_rating,container,false
        )



        return binding.root
    }

}
