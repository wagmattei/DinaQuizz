package com.wagmattei.dynaquiz.ui.quizquestion

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagmattei.dynaquiz.model.Answer
import com.wagmattei.dynaquiz.model.AnswerResult
import com.wagmattei.dynaquiz.model.Question
import com.wagmattei.dynaquiz.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// todo descobrir a melhor forma de acessar o arquivo de recursos, se é mandando o contexto no construtor ou no metodo

class QuizzQuestionViewModel (private val repository: Repository) : ViewModel() {
    val myQuestion: MutableLiveData<Response<Question>> = MutableLiveData()
    val myResult: MutableLiveData<Response<AnswerResult>> = MutableLiveData()
    val myErrorAnswer: MutableLiveData<String> = MutableLiveData()
    val myErrorQuestion: MutableLiveData<String> = MutableLiveData()
    val myQuestionCount : MutableLiveData<Int> = MutableLiveData(0)
    val myHits: MutableLiveData<Int> = MutableLiveData(0)

    fun getQuestion() {

        val request = repository.getQuestion()
        request.enqueue(object : Callback<Question> {
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                if(response.isSuccessful) {
                    myQuestion.postValue(response)
                    myQuestionCount.postValue(myQuestionCount.value?.inc())
                    Log.i("Nova pergunta",""+myQuestionCount.value)
                } else {
                    myErrorQuestion.postValue("Não foi possível obter a pergunta.\n"+response.code())
                }
            }

            override fun onFailure(call: Call<Question>, t: Throwable) {
                myErrorQuestion.postValue("Não foi possível obter a pergunta.")
            }

        })

    }

    fun getAnswer(questionId: Int, answer: Answer) {
        val request = repository.getAnswer(questionId, answer)
        request.enqueue(object : Callback<AnswerResult> {
            override fun onResponse(call: Call<AnswerResult>, response: Response<AnswerResult>) {
                if(response.isSuccessful) {
                    myResult.postValue(response)
                    if(response.body()?.result == "true") {
                        myHits.value = myHits.value?.inc()
                        val answerCount = myHits.value
                        Log.i("Nova Resposta:", "Acertos $answerCount")
                    }
                } else {
                    myErrorAnswer.postValue("Não foi possível obter a resposta.\n"+response.code())
                }
            }

            override fun onFailure(call: Call<AnswerResult>, t: Throwable) {
                myErrorAnswer.postValue("Não foi possível obter a resposta.")
            }

        })
    }
}