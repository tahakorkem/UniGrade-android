package com.tahakorkem.unigrade.data

import com.tahakorkem.unigrade.R

sealed class Term : Comparable<Term> {
    abstract val year: Year
    abstract val lectures: List<Lecture>
    abstract val semesterNameResId: Int

    abstract fun clone(
        year: Year = this.year,
        lectures: List<Lecture> = this.lectures
    ): Term

    val totalCredits
        get() = lectures.fold(0f) { acc, lecture -> acc + lecture.credits }

    val totalCreditsHavingGrade
        get() = lectures.fold(0f) { acc, lecture -> acc + (if (lecture.grade == null) 0f else lecture.credits) }

    val totalWeightedGradePoints
        get() = lectures.fold(0f) { acc, lecture -> acc + (lecture.grade?.let { it.points * lecture.credits } ?: 0f) }

    val averageGrade
        get() = totalWeightedGradePoints / totalCreditsHavingGrade

    data class FallTerm(
        override val year: Year,
        override val lectures: List<Lecture> = emptyList(),
    ) : Term() {
        override val semesterNameResId: Int
            get() = R.string.semester_fall

        override fun clone(year: Year, lectures: List<Lecture>): Term {
            return copy(year = year, lectures = lectures)
        }

        override fun compareTo(other: Term): Int {
            return year.compareTo(other.year)
        }
    }

    data class SpringTerm(
        override val year: Year,
        override val lectures: List<Lecture> = emptyList(),
    ) : Term() {
        override val semesterNameResId: Int
            get() = R.string.semester_spring

        override fun clone(year: Year, lectures: List<Lecture>): Term {
            return copy(year = year, lectures = lectures)
        }

        override fun compareTo(other: Term): Int {
            return year.compareTo(other.year)
        }
    }

    data class SummerTerm(
        override val year: Year,
        override val lectures: List<Lecture> = emptyList(),
    ) : Term() {
        override val semesterNameResId: Int
            get() = R.string.semester_summer

        override fun clone(year: Year, lectures: List<Lecture>): Term {
            return copy(year = year, lectures = lectures)
        }

        override fun compareTo(other: Term): Int {
            return year.compareTo(other.year)
        }
    }

    data class Year(private val beginning: Int) : Comparable<Year> {
        private val end: Int get() = beginning + 1
        override fun toString(): String {
            return "$beginning-$end"
        }

        override fun compareTo(other: Year): Int {
            return beginning.compareTo(other.beginning)
        }
    }

}