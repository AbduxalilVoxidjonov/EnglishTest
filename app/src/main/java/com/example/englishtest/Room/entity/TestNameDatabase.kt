package com.example.englishtest.Room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class TestNameDatabase(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
)
