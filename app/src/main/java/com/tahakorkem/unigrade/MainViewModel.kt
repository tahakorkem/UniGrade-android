package com.tahakorkem.unigrade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tahakorkem.unigrade.data.source.LectureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val lectureRepository: LectureRepository
) : ViewModel() {

    init {
        fetchAll()
    }

    private fun fetchAll() = viewModelScope.launch {
        val result = lectureRepository.getAll()
    }

}