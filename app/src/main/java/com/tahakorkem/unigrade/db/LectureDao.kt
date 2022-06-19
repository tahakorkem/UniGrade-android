package com.tahakorkem.unigrade.db

import androidx.room.Dao
import androidx.room.Insert
import com.tahakorkem.unigrade.data.dto.LectureModel

@Dao
interface LectureDao {

    @Insert
    suspend fun insertLecture(lecture: LectureModel)

}