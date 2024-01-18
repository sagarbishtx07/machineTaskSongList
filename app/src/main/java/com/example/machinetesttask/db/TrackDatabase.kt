package com.example.machinetesttask.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.machinetesttask.model.Item

@Database(
    entities = [Item::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class TrackDatabase : RoomDatabase() {
    abstract fun getAlbumsDao(): TracksDao

    companion object {

        @Volatile
        private var instance: TrackDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = Companion.instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TrackDatabase::class.java,
                "trackdatabase.db"
            ).build()
    }
}