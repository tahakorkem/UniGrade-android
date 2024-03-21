package com.tahakorkem.unigrade.ui.screen.lecture.addoredit

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.tahakorkem.unigrade.R
import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.ui.screen.lecture.list.Loading

@Composable
fun AddOrEditLectureScreen(
    courseCode: String? = null,
    navigateUp: () -> Unit,
    viewModel: AddOrEditViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            id = if (courseCode == null) R.string.app_bar_new_lecture else R.string.app_bar_edit_lecture
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(imageVector = Icons.Rounded.Close, contentDescription = null)
                    }
                },
            )
        },
    ) {
        when {
            uiState.isLoading -> Loading()
            else -> LectureFormContent(uiState.lecture!!)
        }
    }
}

@Composable
fun LectureFormContent(lecture: Lecture) {
    Text(text = lecture.toString())
}
