package com.tahakorkem.unigrade.data

data class Total(
    val terms: List<Term>
) {

    val totalCredits
        get() = terms.fold(0f) { acc, term -> acc + term.totalCredits }

    val totalCreditsHavingGrade
        get() = terms.fold(0f) { acc, term -> acc + term.totalCreditsHavingGrade }

    val totalWeightedGradePoints
        get() = terms.fold(0f) { acc, term -> acc + term.totalWeightedGradePoints }

    val averageGrade
        get() = totalWeightedGradePoints / totalCreditsHavingGrade

}