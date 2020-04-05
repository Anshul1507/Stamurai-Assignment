package com.example.stamurai_assignment.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stamurai_assignment.R
import com.example.stamurai_assignment.database.RatingDatabase
import com.example.stamurai_assignment.databinding.FragmentHistoryBinding
import com.google.android.material.snackbar.Snackbar


class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val binding: FragmentHistoryBinding= DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_history,container,false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = RatingDatabase.getInstance(application).ratingDatabaseDao

        val viewModelPastData = PastDataViewModelFactory(dataSource,application)
        val pastDataViewModel =
            ViewModelProvider(
                this,viewModelPastData
            ).get(PastDataViewModel::class.java)
        binding.pastDataViewModel = pastDataViewModel
        binding.setLifecycleOwner { lifecycle }

        pastDataViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if(it == true){
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                pastDataViewModel.doneShowingSnackbar()
            }
        })

        return binding.root
    }

}
