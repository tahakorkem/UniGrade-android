package com.tahakorkem.unigrade.data

data class Lecture(
    val code: String,
    val grade: Grade,
    val credits: Float,
    val gradingScheme: GradingScheme
)
