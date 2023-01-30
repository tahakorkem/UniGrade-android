package com.tahakorkem.unigrade.data.source.impl

import com.tahakorkem.unigrade.FakeItems
import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.Result
import com.tahakorkem.unigrade.data.Term
import com.tahakorkem.unigrade.data.Total
import com.tahakorkem.unigrade.data.source.LectureRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class FakeLectureRepository : LectureRepository {

    private var total = MutableStateFlow(Total(terms = FakeItems.terms))

    override fun observeAll(): Flow<Total> {
        return total.onEach {
            delay(500)
        }
    }

    override fun observeLecture(lectureCode: String): Flow<Lecture> {
        return total.map {
            it.terms.flatMap { it.lectures }.find { it.code == lectureCode }
                ?: error("Lecture not found")
        }.onEach { delay(200) }
    }

    override suspend fun insertLecture(lecture: Lecture, whichTerm: Term): Result<Unit> {
        val index =
            total.value.terms.indexOfFirst { it.javaClass == whichTerm.javaClass && it.year == whichTerm.year }

        // If the term doesn't exist, add it to the list with the lecture
        // Otherwise, add the lecture to the term's list of lectures
        if (index == -1) {
            total.update {
                it.copy(terms = it.terms.toMutableList().apply {
                    val i = this.indexOfFirst { whichTerm < it }
                    if (i == -1) {
                        this.add(whichTerm.clone(lectures = listOf(lecture)))
                    } else {
                        this.add(i, whichTerm.clone(lectures = listOf(lecture)))
                    }
                })
            }
        } else {
            total.update {
                it.copy(terms = it.terms.toMutableList().apply {
                    this[index] = this[index].clone(lectures = this[index].lectures + lecture)
                })
            }
        }

        return Result.Success(Unit)
    }

    override suspend fun updateLecture(lecture: Lecture): Result<Unit> {
        TODO("Not yet implemented")
    }

}

class TermNotFoundException : Exception()
