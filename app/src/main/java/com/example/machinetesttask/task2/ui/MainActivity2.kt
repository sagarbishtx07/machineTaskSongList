package com.example.machinetesttask.task2.ui

import MatchItemModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.machinetesttask.R
import com.example.machinetesttask.databinding.ActivityMain2Binding
import com.example.machinetesttask.task2.adapter.MatchAdapter

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val matchAdapter = MatchAdapter()
        val matchItemList: ArrayList<MatchItemModel> = ArrayList()
        for (i in 1..4) {
            matchItemList.add(
                MatchItemModel(
                    id = i,
                    dateTime = "Sat, 16th Dec 2023 | 22:30:00 IST",
                    header = "India tour of South Africa, 2023-24",
                    team1 = "India",
                    team1Img = R.drawable.team1,
                    team1Over = "14.5 overs",
                    team1Score = "150/3",
                    team2 = "South Africa",
                    team2Img = R.drawable.team2,
                    team2Over = "10 overs",
                    team2Score = "120/5",
                    baseImg = R.drawable.sky1,
                    baseContent = "Suryakumar Yadav equals all-time T20I record with century against South Africa : ICC"
                )
            )
        }
        matchAdapter.differ.submitList(matchItemList)
        binding.task2rvMain.apply {
            adapter = matchAdapter
            layoutManager = LinearLayoutManager(this@MainActivity2)

        }

    }
}