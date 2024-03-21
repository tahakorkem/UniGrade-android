package com.tahakorkem.unigrade.util

fun Float.toPrettyString(): String {
    return if (this == this.toInt().toFloat()) this.toInt().toString()
    else "%.2f".format( this)
}