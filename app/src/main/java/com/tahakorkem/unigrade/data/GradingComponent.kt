package com.tahakorkem.unigrade.data

import androidx.annotation.FloatRange

data class GradingComponent(
    val type: Type,
    @FloatRange(from = 0.0, to = 100.0) val score: Float,
    @FloatRange(from = 0.0, to = 1.0) val weight: Float,
) {
    val weightedScore
        get() = score * weight


    sealed class Type {
        abstract val name: String

        object Assignment : Type() {
            override val name: String get() = "Assignment"
        }

        object Quiz : Type() {
            override val name: String get() = "Quiz"
        }

        object MidtermExam : Type() {
            override val name: String get() = "Midterm"
        }

        object FinalExam : Type() {
            override val name: String get() = "Final"
        }

        data class Custom(override val name: String) : Type()

    }

}

typealias GradingScheme = List<GradingComponent>