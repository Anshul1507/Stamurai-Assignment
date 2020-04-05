package com.example.stamurai_assignment.homepage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.stamurai_assignment.MainActivity
import com.example.stamurai_assignment.R
import com.example.stamurai_assignment.databinding.FragmentMainBinding

/**
 * Created on 05-04-2020
 * By Anshul1507
 */
class MainFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,container,false
        )
        var minRating: Int? = 0
        var maxRating: Int? = 9

        binding.btnRating.text = "Rating $minRating - $maxRating"

        binding.btnMinMinus.setOnClickListener {
            if(minRating!! > 0){
                minRating--
                binding.btnRating.text = "Rating $minRating - $maxRating"
            }else {
                Toast.makeText(context, "Rating can't be less than 0", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnMinPlus.setOnClickListener {
            if(minRating!! < 9){
                if(minRating<maxRating!!-1){
                    minRating++
                }else{
                    Toast.makeText(context, "Rating difference should be greater than or equal to one", Toast.LENGTH_SHORT).show()
                }

                binding.btnRating.text = "Rating $minRating - $maxRating"
            }else {
                Toast.makeText(context, "Rating can't be more than 9", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnMaxMinus.setOnClickListener {
            if(maxRating!! > 0){
                if(maxRating>minRating!!+1){
                    maxRating--
                }else{
                    Toast.makeText(context, "Rating difference should be greater than or equal to one", Toast.LENGTH_SHORT).show()
                }

                binding.btnRating.text = "Rating $minRating - $maxRating"
            }else {
                Toast.makeText(context, "Rating can't be less than 0", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnMaxPlus.setOnClickListener {
            if(maxRating!! < 9){
                maxRating++
                binding.btnRating.text = "Rating $minRating - $maxRating"
            }else {
                Toast.makeText(context, "Rating can't be more than 9", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRating.setOnClickListener {
            MainActivity.minRating = minRating.toString()
            MainActivity.maxRating = maxRating.toString()
            it.findNavController().navigate(MainFragmentDirections.actionMainFragmentToRatingFragment())
        }

        binding.btnPastRating.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.actionMainFragmentToHistoryFragment())
        }
        return binding.root
    }

}
