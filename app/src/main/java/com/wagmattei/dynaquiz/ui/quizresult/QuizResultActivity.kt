package com.wagmattei.dynaquiz.ui.quizresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wagmattei.dynaquiz.R
import com.wagmattei.dynaquiz.data.UserViewModel
import com.wagmattei.dynaquiz.data.model.User
import com.wagmattei.dynaquiz.util.Constants
import kotlinx.android.synthetic.main.activity_quiz_result.*
import kotlinx.android.synthetic.main.activity_quizz_question.*

class QuizResultActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

        val adapter = QuizResultaAdapter()
        // RecyclerView
        val recyclerView_lastUser = rv_lastUser
        recyclerView_lastUser.adapter = adapter
        recyclerView_lastUser.layoutManager = LinearLayoutManager(this)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // Carregar Usuário
        mUserViewModel.readAllData.observe(this, Observer { user ->
            adapter.setData(user)
        })


    }

    override fun onStart() {
        super.onStart()
        // Nome do usuário - todo enviar para a viewmodel
        val userName = intent.getStringExtra(Constants.USER_NAME).toString()
        val answerCount = intent.getIntExtra(Constants.ANSWER_COUNT, 0)

        tv_loginName.text = userName
        tv_score.text = "Sua pontuação é ${answerCount.toString()} de 10"

        val user = User(0, userName, answerCount)
        mUserViewModel.addUser(user)
        Toast.makeText(this@QuizResultActivity, "Sucesso", Toast.LENGTH_SHORT).show()
    }

}