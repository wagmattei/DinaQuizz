package com.wagmattei.dynaquiz.ui.quizquestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.wagmattei.dynaquiz.R
import com.wagmattei.dynaquiz.ui.quizresult.QuizResultActivity
import kotlinx.android.synthetic.main.activity_quizz_question.*
import kotlinx.android.synthetic.main.dialog_correct.view.*
import kotlinx.android.synthetic.main.teste.view.*

class QuizzQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_question)

        // Only for teste
        btn_sendAnswer.setOnClickListener{
           // startActivity(Intent(this, QuizResultActivity::class.java))
           // finish()

            showDialogAlert(R.layout.dialog_correct)
            showDialogAlert(R.layout.dialog_incorrect)
        }


    }

    private fun showDialogAlert(layout : Int) {

        val view = View.inflate(this@QuizzQuestionActivity, layout, null)

        val builder = AlertDialog.Builder(this@QuizzQuestionActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.setCancelable(false)

        view.dialog_button.setOnClickListener {
            dialog.dismiss()
        }

    }
}