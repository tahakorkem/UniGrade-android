package com.tahakorkem.unigrade.data

import androidx.annotation.FloatRange

enum class Grade(
    @FloatRange(from = 0.0, to = 4.0) val points: Float
) {
    AA(4.0f),
    BA(3.5f),
    BB(3.0f),
    CB(2.5f),
    CC(2.0f),
    DC(1.5f),
    DD(1.0f),
    FD(0.5f),
    FF(0f),
    F0(0f)
}