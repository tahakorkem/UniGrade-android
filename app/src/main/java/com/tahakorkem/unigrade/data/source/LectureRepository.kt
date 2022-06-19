package com.tahakorkem.unigrade.data.source

import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.Term
import com.tahakorkem.unigrade.data.Total

interface LectureRepository {
    suspend fun getAll(): Total
    suspend fun insertLecture(lecture: Lecture, whichTerm: Term)
    suspend fun updateLecture(lecture: Lecture)
}