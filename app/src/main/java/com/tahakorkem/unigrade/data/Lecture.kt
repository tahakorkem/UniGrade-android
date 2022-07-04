package com.tahakorkem.unigrade.data

data class Lecture(
    val name: String,
    val code: String,
    val grade: Grade?,
    val credits: Float,
    val gradingScheme: GradingScheme
)
