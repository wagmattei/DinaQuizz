package com.wagmattei.dynaquiz.ui.quizquestion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.wagmattei.dynaquiz.R
import com.wagmattei.dynaquiz.model.Answer
import com.wagmattei.dynaquiz.repository.Repository
import com.wagmattei.dynaquiz.ui.LoadingDialog.LoadingDialog
import com.wagmattei.dynaquiz.ui.quizresult.QuizResultActivity
import com.wagmattei.dynaquiz.util.Constants.Companion.ANSWER_COUNT
import com.wagmattei.dynaquiz.util.Constants.Companion.USER_NAME
import kotlinx.android.synthetic.main.activity_quizz_question.*
import kotlinx.android.synthetic.main.dialog_correct.view.*


class QuizzQuestionActivity : AppCompatActivity() {

    private lateinit var viewModel: QuizzQuestionViewModel
    private lateinit var userName : String
    private var myAnswer : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_question)

        val viewModelFactory = QuizzQuestionViewModelFactory(Repository())
        val viewModel = ViewModelProvider(this, viewModelFactory).get(QuizzQuestionViewModel::class.java)
        val loading = LoadingDialog(this)

        loading.startLoading()
        clearOption()
        viewModel.getQuestion()

        // Observando Pergunta
        viewModel.myQuestion.observe(this, { response ->
            loading.isDismiss()

            if (response.isSuccessful) {
                response.body()?.let {
                    txt_question.text = response.body()?.statement.toString()
                    quizzOption1.text = response.body()?.options?.get(0).toString()
                    quizzOption2.text = response.body()?.options?.get(1).toString()
                    quizzOption3.text = response.body()?.options?.get(2).toString()
                    quizzOption4.text = response.body()?.options?.get(3).toString()
                    quizzOption5.text = response.body()?.options?.get(4).toString()
                }
            } else {
                Toast.makeText(this, getString(R.string.alert_could_not_load_question), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.myResult.observe(this , { response ->
            loading.isDismiss()
            val respLayout = if(response.body()?.result=="true"){ R.layout.dialog_correct } else { R.layout.dialog_incorrect }

            val view = View.inflate(this@QuizzQuestionActivity, respLayout, null)
            val builder = AlertDialog.Builder(this@QuizzQuestionActivity)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
            dialog.setCancelable(false)
            view.dialog_button.setOnClickListener {
                dialog.dismiss()
                if(viewModel.myQuestionCount.value == 10) {
                    val i = Intent(this@QuizzQuestionActivity,QuizResultActivity::class.java)
                    i.putExtra(USER_NAME, userName)
                    i.putExtra(ANSWER_COUNT, viewModel.myHits.value)
                    startActivity(i)
                    finish()
                }
                loading.startLoading()
                clearOption()
                viewModel.getQuestion()
            }
        })


        btn_sendAnswer.setOnClickListener {
            sv_quizzquestion.fullScroll(ScrollView.FOCUS_UP);
             myAnswer =
                when {
                    quizzOption1.isChecked -> {
                        quizzOption1.text.toString()
                    }
                    quizzOption2.isChecked -> {
                        quizzOption2.text.toString()
                    }
                    quizzOption3.isChecked -> {
                        quizzOption3.text.toString()
                    }
                    quizzOption4.isChecked -> {
                        quizzOption4.text.toString()
                    }
                    quizzOption5.isChecked -> {
                        quizzOption5.text.toString()
                    }
                    else -> ""
                }

            loading.startLoading()
            viewModel.getAnswer(viewModel.myQuestion.value?.body()?.id!!, Answer(myAnswer))

        }

        viewModel.myErrorQuestion.observe(this , { errorQuestion ->
            loading.isDismiss()
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.oops))
            builder.setMessage(errorQuestion.toString())
            builder.setPositiveButton(getString(R.string.try_again) ) { dialog, which ->
                dialog.dismiss()
                loading.startLoading()
                clearOption()
                viewModel.getQuestion()
            }
            builder.setNegativeButton(getString(R.string.exit) ) { dialog, which ->
                dialog.dismiss()
                finish()
            }
            builder.setCancelable(false)
            val dialog = builder.create()
            dialog.show()
        })

        viewModel.myErrorAnswer.observe(this , { errorQuestion ->
            loading.isDismiss()
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.oops))
            builder.setMessage(errorQuestion.toString())
            builder.setPositiveButton(getString(R.string.try_again) ) { dialog, which ->
                dialog.dismiss()
                loading.startLoading()
                viewModel.getAnswer(viewModel.myQuestion.value?.body()?.id!!, Answer(myAnswer))
            }
            builder.setNegativeButton(getString(R.string.exit) ) { dialog, which ->
                dialog.dismiss()
                finish()
            }
            builder.setCancelable(false)
            val dialog = builder.create()
            dialog.show()
        })
    }

    fun clearOption() {
        quizzGroup.clearCheck()
    }

    override fun onStart() {
        super.onStart()

        userName = intent.getStringExtra(USER_NAME).toString()

    }



}