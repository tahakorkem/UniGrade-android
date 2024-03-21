package com.tahakorkem.unigrade

import com.tahakorkem.unigrade.data.Grade
import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.Term

object FakeItems {
    val terms = listOf(
        Term.SpringTerm(
            lectures = listOf(
                Lecture(
                    name = "Bilgisayar Bilimlerine Giriş",
                    code = "BLM1011",
                    grade = Grade.BB,
                    credits = 4.0f,
                    gradingScheme = listOf()
                ),
                Lecture(
                    name = "Matematik 1",
                    code = "BLM1072",
                    grade = Grade.FF,
                    credits = 4.0f,
                    gradingScheme = listOf()
                ),
                Lecture(
                    name = "Sayısal Analiz",
                    code = "BLM1022",
                    grade = null,
                    credits = 3.0f,
                    gradingScheme = listOf()
                ),
            ),
            year = Term.Year(beginning = 2019)
        ),
        Term.SummerTerm(
            lectures = listOf(
                Lecture(
                    name = "Türkçe 1",
                    code = "BLM1111",
                    grade = Grade.AA,
                    credits = 0f,
                    gradingScheme = listOf()
                ),
                Lecture(
                    name = "Veritabanı Yönetimi",
                    code = "BLM1172",
                    grade = Grade.BA,
                    credits = 4.0f,
                    gradingScheme = listOf()
                ),
                Lecture(
                    name = "Doğal Dil İşlemeye Giriş",
                    code = "BLM1122",
                    grade = Grade.DC,
                    credits = 3.0f,
                    gradingScheme = listOf()
                ),
            ),
            year = Term.Year(beginning = 2021)
        )
    )
}