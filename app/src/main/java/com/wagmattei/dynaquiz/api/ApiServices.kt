package com.wagmattei.dynaquiz.api

import com.wagmattei.dynaquiz.model.Answer
import com.wagmattei.dynaquiz.model.AnswerResult
import com.wagmattei.dynaquiz.model.Question
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {
    @GET("question")
    fun getQuestion(): Call<Question>

    @POST("answer")
    fun getAnswer(
        @Query("questionId") questionId : Int,
        @Body answer: Answer
    ): Call<AnswerResult>
}