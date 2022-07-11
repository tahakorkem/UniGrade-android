package com.tahakorkem.unigrade.ui.screen.lecturelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tahakorkem.unigrade.data.Lecture
import com.tahakorkem.unigrade.data.Term
import com.tahakorkem.unigrade.data.Total
import com.tahakorkem.unigrade.data.source.LectureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LectureListViewModel @Inject constructor(
    private val lectureRepository: LectureRepository
) : ViewModel() {

    val uiState = lectureRepository.observeAll()
        .map { LectureListUiState(total = it, loading = false) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, LectureListUiState(loading = true))


    fun insert(lecture: Lecture, term: Term) = viewModelScope.launch {
        lectureRepository.insertLecture(lecture, term)
    }


    data class LectureListUiState(
        val total: Total? = null,
        val loading: Boolean = false,
    )

}