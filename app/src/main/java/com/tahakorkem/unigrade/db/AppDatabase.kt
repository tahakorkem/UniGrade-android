package com.tahakorkem.unigrade.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tahakorkem.unigrade.data.dto.LectureModel

@Database(entities = [LectureModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lectureDao(): LectureDao

}