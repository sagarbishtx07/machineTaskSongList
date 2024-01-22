package com.example.machinetesttask.task2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.machinetesttask.R
import com.example.machinetesttask.databinding.ActivityMatchDetailsBinding

class MatchDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityMatchDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}