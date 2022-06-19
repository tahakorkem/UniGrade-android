package com.tahakorkem.unigrade.data.source.impl

import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.Term
import com.tahakorkem.unigrade.data.Total
import com.tahakorkem.unigrade.data.source.LectureRepository
import kotlinx.coroutines.delay

class FakeLectureRepository : LectureRepository {

    private var total = Total(terms = listOf())

    override suspend fun getAll(): Total {
        delay(200)
        return total
    }

    override suspend fun insertLecture(lecture: Lecture, whichTerm: Term) {
        delay(100)
        val found = total.terms.find {
            it.toString() == whichTerm.toString()
        }
        if(found == null) {
            total = total.copy(terms = total.terms.plus(whichTerm))
        } else {

        }
    }

    override suspend fun updateLecture(lecture: Lecture) {
        TODO("Not yet implemented")
    }
}