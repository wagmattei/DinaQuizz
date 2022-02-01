package com.wagmattei.dynaquiz.repository


import com.wagmattei.dynaquiz.api.RetrofitInstance
import com.wagmattei.dynaquiz.model.Answer

class Repository {
    fun getQuestion() = RetrofitInstance.api.getQuestion()

    fun getAnswer(questionId: Int,answer: Answer) = RetrofitInstance.api.getAnswer(questionId, answer)
}