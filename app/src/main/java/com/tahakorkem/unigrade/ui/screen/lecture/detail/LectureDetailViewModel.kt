package com.tahakorkem.unigrade.ui.screen.lecture.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.source.LectureRepository
import com.tahakorkem.unigrade.ui.NavDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LectureDetailViewModel
@Inject constructor(
    private val lectureRepository: LectureRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val lectureCode = savedStateHandle.get<String>(NavDestinations.Lecture.Detail.KEY_CODE)!!
    val uiState = lectureRepository
        .observeLecture(lectureCode)
        .map { LectureDetailUiState(lecture = it, isLoading = false) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, LectureDetailUiState(isLoading = true))
}

data class LectureDetailUiState(
    val lecture: Lecture? = null,
    val isLoading: Boolean = false
)