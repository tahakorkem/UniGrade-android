package com.tahakorkem.unigrade.ui.screen.lecturedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tahakorkem.unigrade.R
import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.ui.screen.lecturelist.Loading
import com.tahakorkem.unigrade.util.toPrettyString

@Composable
fun LectureDetailScreen(
    courseCode: String,
    viewModel: LectureDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_bar_lecture_detail)) })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { }
            ) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
            }
        },
    ) {
        if (uiState.isLoading) {
            Loading()
        } else {
            LectureDetailContent(uiState.lecture!!)
        }
    }
}

@Composable
fun LectureDetailContent(lecture: Lecture) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(text = buildAnnotatedString {
            pushStyle(style = SpanStyle(fontWeight = FontWeight.Medium))
            append("Ders Kodu: ")
            pop()
            append(lecture.code)
        })
        Text(text = buildAnnotatedString {
            pushStyle(style = SpanStyle(fontWeight = FontWeight.Medium))
            append("Ders Adı: ")
            pop()
            append(lecture.name)
        })
        Text(text = buildAnnotatedString {
            pushStyle(style = SpanStyle(fontWeight = FontWeight.Medium))
            append("Ders Kredisi: ")
            pop()
            append(lecture.credits.toPrettyString())
        })
        Text(text = buildAnnotatedString {
            pushStyle(style = SpanStyle(fontWeight = FontWeight.Medium))
            append("Ders Notu: ")
            pop()
            append(stringResource(id = lecture.grade?.localizedTextResId ?: R.string.pending))
        })
    }
}
