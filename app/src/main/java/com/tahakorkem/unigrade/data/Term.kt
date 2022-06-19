package com.tahakorkem.unigrade.data

sealed class Term {
    abstract val lectures: List<Lecture>
    abstract val year: Year

    val totalCredits
        get() = lectures.fold(0f) { acc, lecture -> acc + lecture.credits }

    val totalGradePoints
        get() = lectures.fold(0f) { acc, lecture -> acc + lecture.grade.points }

    val averageGrade
        get() = totalGradePoints / totalCredits

    data class FallTerm(
        override val lectures: List<Lecture>,
        override val year: Year
    ) : Term()

    data class SpringTerm(
        override val lectures: List<Lecture>,
        override val year: Year
    ) : Term()

    data class SummerTerm(
        override val lectures: List<Lecture>,
        override val year: Year
    ) : Term()

    class Year(private val beginning: Int) {
        private val end: Int get() = beginning + 1
        override fun toString(): String {
            return "$beginning-$end"
        }
    }

}