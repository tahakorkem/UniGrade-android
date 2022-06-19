package com.tahakorkem.unigrade.data.source.impl

import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.Term
import com.tahakorkem.unigrade.data.Total
import com.tahakorkem.unigrade.data.source.LectureRepository
import com.tahakorkem.unigrade.db.LectureDao

class LectureRepositoryImpl(private val lectureDao: LectureDao) : LectureRepository {
    override suspend fun getAll(): Total {
        TODO()
        //return lectureDao.getAll()
    }

    override suspend fun insertLecture(lecture: Lecture, whichTerm: Term) {
        TODO("Not yet implemented")
        //lectureDao.insertLecture()
    }

    override suspend fun updateLecture(lecture: Lecture) {
        TODO("Not yet implemented")
        //lectureDao.updateLecture()
    }
}