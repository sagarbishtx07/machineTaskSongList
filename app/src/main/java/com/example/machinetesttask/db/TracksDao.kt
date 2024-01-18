package com.example.machinetesttask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.machinetesttask.model.Item

@Dao
interface TracksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(tracks:List<Item>)

    @Query("SELECT * FROM items")
    fun getItems():List<Item>

}