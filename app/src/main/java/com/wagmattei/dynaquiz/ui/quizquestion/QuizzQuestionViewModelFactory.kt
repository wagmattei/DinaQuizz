package com.wagmattei.dynaquiz.ui.quizquestion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wagmattei.dynaquiz.repository.Repository

class QuizzQuestionViewModelFactory (
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuizzQuestionViewModel(repository) as T
    }
}