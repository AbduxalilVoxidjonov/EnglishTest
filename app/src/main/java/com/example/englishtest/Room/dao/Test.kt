package com.example.englishtest.Room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.englishtest.Room.entity.TestDatabase


@Dao
interface Test {
    @Insert
    fun addTest(test: TestDatabase): Long

    @Query("Select * from testdatabase")
    fun getAllTest(): List<TestDatabase>

    @Query("Select * from testdatabase where id = :id")
    fun getTestById(id: Int): TestDatabase

    @Query("select * from testdatabase where test_id = :id")
    fun getTestByTestId(id: Int): List<TestDatabase>

}