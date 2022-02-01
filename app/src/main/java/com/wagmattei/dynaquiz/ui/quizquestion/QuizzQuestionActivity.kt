package com.wagmattei.dynaquiz.ui.quizquestion

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.wagmattei.dynaquiz.R
import kotlinx.android.synthetic.main.activity_quizz_question.*
import kotlinx.android.synthetic.main.dialog_correct.view.*


class QuizzQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_question)

        // Only for teste
        btn_sendAnswer.setOnClickListener {
            // startActivity(Intent(this, QuizResultActivity::class.java))
            // finish()

            showDialogAlert(R.layout.dialog_correct)
            showDialogAlert(R.layout.dialog_incorrect)
        }

    }

    private fun showDialogAlert(layout: Int) {

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