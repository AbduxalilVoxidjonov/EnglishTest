package com.example.englishtest.Room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.englishtest.Room.entity.TestNameDatabase


@Dao
interface TestName {
    @Insert
    fun addName(testNameDatabase: TestNameDatabase):Long

    @Query("SELECT * FROM TestNameDatabase")
    fun getAllName(): List<TestNameDatabase>

}