package com.tahakorkem.unigrade.data

data class Total(
    val terms: List<Term>
) {

    val totalCredits
        get() = terms.fold(0f) { acc, term -> acc + term.totalCredits }

    val totalGradePoints
        get() = terms.fold(0f) { acc, term -> acc + term.totalGradePoints }

    val averageGrade
        get() = totalGradePoints / totalCredits

}