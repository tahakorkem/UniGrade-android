package com.tahakorkem.unigrade.data

import androidx.annotation.FloatRange
import androidx.annotation.StringRes
import com.tahakorkem.unigrade.R

enum class Grade(
    @FloatRange(from = 0.0, to = 4.0) val points: Float,
    @StringRes val localizedTextResId: Int
) {
    AA(4.0f, R.string.localized_text_aa),
    BA(3.5f, R.string.localized_text_ba),
    BB(3.0f, R.string.localized_text_bb),
    CB(2.5f, R.string.localized_text_cb),
    CC(2.0f, R.string.localized_text_cc),
    DC(1.5f, R.string.localized_text_dc),
    DD(1.0f, R.string.localized_text_dd),
    FD(0.5f, R.string.localized_text_fd),
    FF(0f, R.string.localized_text_ff),
    F0(0f, R.string.localized_text_f0)
}