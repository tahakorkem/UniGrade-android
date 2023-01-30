package com.tahakorkem.unigrade.data

data class Lecture(
    val name: String = "",
    val code: String = "",
    val grade: Grade? = null,
    val credits: Float = 0f,
    val gradingScheme: GradingScheme = emptyList(),
)
