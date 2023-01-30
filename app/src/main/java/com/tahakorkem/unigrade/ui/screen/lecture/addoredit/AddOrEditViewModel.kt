package com.tahakorkem.unigrade.ui.screen.lecture.addoredit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tahakorkem.unigrade.data.Grade
import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.source.LectureRepository
import com.tahakorkem.unigrade.ui.NavDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AddOrEditViewModel @Inject constructor(
    private val lectureRepository: LectureRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val lectureCode = savedStateHandle.get<String>(NavDestinations.Lecture.Edit.KEY_CODE)

    private val _uiState = MutableStateFlow(AddOrEditLectureUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        populate()
    }

    private fun populate() {
        if (lectureCode == null) {
            _uiState.value = AddOrEditLectureUiState(lecture = Lecture(), isLoading = false)
            return
        }
        lectureRepository.observeLecture(lectureCode)
            .map { AddOrEditLectureUiState(lecture = it, isLoading = false) }
//            .catch { _uiState.value = AddOrEditLectureUiState(isLoading = false) }
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }

    private fun onCodeChange(code: String) {
        _uiState.update { it.copy(lecture = it.lecture!!.copy(code = code)) }
    }

    private fun onNameChange(name: String) {
        _uiState.update { it.copy(lecture = it.lecture!!.copy(name = name)) }
    }

    private fun onCreditsChange(credits: Float) {
        _uiState.update { it.copy(lecture = it.lecture!!.copy(credits = credits)) }
    }

    private fun onGradeChange(grade: Grade) {
        _uiState.update { it.copy(lecture = it.lecture!!.copy(grade = grade)) }
    }

    private fun saveLecture(lecture: Lecture) {
//        if (lectureCode == null) {
//            lectureRepository.addLecture(lecture)
//        } else {
//            lectureRepository.updateLecture(lecture)
//        }
    }

}

data class AddOrEditLectureUiState(
    val lecture: Lecture? = null,
    val isLoading: Boolean = false
)