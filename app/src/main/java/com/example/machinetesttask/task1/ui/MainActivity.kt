package com.example.machinetesttask.task1.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.machinetesttask.task1.adapter.TrackItemAdapter
import com.example.machinetesttask.databinding.ActivityMainBinding
import com.example.machinetesttask.task1.db.TrackDatabase
import com.example.machinetesttask.task1.repo.Repository
import com.example.machinetesttask.task1.utils.Resource

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: TracksViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(TrackDatabase(this))
        val viewModelProviderFactory = TracksViewModelProviderFactory(repository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(TracksViewModel::class.java)

        val songAdapter = TrackItemAdapter()
        binding.rvMain.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }


        viewModel.albums.observe(this, Observer {
            when (it) {
                is Resource.Sucess -> {
                    binding.pbMain.visibility = View.GONE
                    it.response.let {
                        viewModel.getSavedItemsfromRepo().observe(this, Observer { savedItems ->
                            // Update your adapter or UI with savedItems
                            songAdapter.differ.submitList(savedItems)
                            Log.d("SavedItems","$savedItems")
                        })
                    }
                }

                is Resource.Error -> {
                    binding.pbMain.visibility = View.GONE
                    Log.d("ApiError", it.message.toString())
                }

                is Resource.Loading -> {
                    binding.pbMain.visibility = View.VISIBLE
                }
                else->{
                    Log.d("ApiError", "ELSE BRANCH")
                }
            }
        })

    }
}