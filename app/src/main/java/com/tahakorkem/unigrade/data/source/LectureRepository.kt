package com.tahakorkem.unigrade.data.source

import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.Result
import com.tahakorkem.unigrade.data.Term
import com.tahakorkem.unigrade.data.Total
import kotlinx.coroutines.flow.Flow

interface LectureRepository {
    fun observeAll(): Flow<Total>
    fun observeLecture(lectureCode: String): Flow<Lecture>
    suspend fun insertLecture(lecture: Lecture, whichTerm: Term): Result<Unit>
    suspend fun updateLecture(lecture: Lecture): Result<Unit>
}