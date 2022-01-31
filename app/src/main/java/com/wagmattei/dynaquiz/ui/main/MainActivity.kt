package com.wagmattei.dynaquiz.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wagmattei.dynaquiz.R
import com.wagmattei.dynaquiz.ui.quizquestion.QuizzQuestionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideActionBar()

        // Only for teste, replace it
        btn_start.setOnClickListener{
            startActivity(Intent(this, QuizzQuestionActivity::class.java))
        }

    }

    private fun hideActionBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide();
    }
}