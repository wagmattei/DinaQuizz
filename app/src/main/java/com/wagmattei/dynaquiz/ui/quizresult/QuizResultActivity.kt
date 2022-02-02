package com.wagmattei.dynaquiz.ui.quizresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wagmattei.dynaquiz.R
import com.wagmattei.dynaquiz.util.Constants
import kotlinx.android.synthetic.main.activity_quiz_result.*
import kotlinx.android.synthetic.main.activity_quizz_question.*

class QuizResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

    }

    override fun onStart() {
        super.onStart()
        // Nome do usuário - todo enviar para a viewmodel
        val userName = intent.getStringExtra(Constants.USER_NAME).toString()
        val answerCount = intent.getIntExtra(Constants.ANSWER_COUNT,0).toString()

        tv_loginName.text = userName
        tv_score.text = "Sua pontuação é $answerCount de 10"

    }
}