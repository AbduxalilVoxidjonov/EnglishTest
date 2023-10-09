package com.example.englishtest.Room.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.englishtest.Room.dao.Test
import com.example.englishtest.Room.dao.TestName
import com.example.englishtest.Room.entity.TestDatabase
import com.example.englishtest.Room.entity.TestNameDatabase

@Database(entities = [TestNameDatabase::class, TestDatabase::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun testDao(): Test
    abstract fun testNameDao(): TestName

    companion object {
        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = androidx.room.Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "test.db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return appDatabase!!
        }
    }
}