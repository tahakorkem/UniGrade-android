package com.tahakorkem.unigrade.data.source.impl

import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.Result
import com.tahakorkem.unigrade.data.Term
import com.tahakorkem.unigrade.data.Total
import com.tahakorkem.unigrade.data.source.LectureRepository
import com.tahakorkem.unigrade.db.LectureDao
import kotlinx.coroutines.flow.Flow

class LectureRepositoryImpl(private val lectureDao: LectureDao) : LectureRepository {
    override fun observeAll(): Flow<Total> {
        TODO("Not yet implemented")
    }

    override fun observeLecture(lectureCode: String): Flow<Lecture> {
        TODO("Not yet implemented")
    }

    override suspend fun insertLecture(lecture: Lecture, whichTerm: Term): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateLecture(lecture: Lecture): Result<Unit> {
        TODO("Not yet implemented")
    }

}