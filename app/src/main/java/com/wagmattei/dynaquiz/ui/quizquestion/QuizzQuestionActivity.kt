package com.wagmattei.dynaquiz.ui.quizquestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wagmattei.dynaquiz.R
import com.wagmattei.dynaquiz.ui.quizresult.QuizResultActivity
import kotlinx.android.synthetic.main.activity_quizz_question.*

class QuizzQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_question)

        // Only for teste
        btn_sendAnswer.setOnClickListener{
            startActivity(Intent(this, QuizResultActivity::class.java))
            finish()
        }

    }
}