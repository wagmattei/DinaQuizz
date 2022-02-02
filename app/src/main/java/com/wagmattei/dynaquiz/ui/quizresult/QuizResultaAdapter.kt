package com.wagmattei.dynaquiz.ui.quizresult

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wagmattei.dynaquiz.R
import com.wagmattei.dynaquiz.data.model.User
import kotlinx.android.synthetic.main.quiz_result_last_users.view.*

class QuizResultaAdapter: RecyclerView.Adapter<QuizResultaAdapter.QuizResultViewHolder>() {

    private var lastUserList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizResultViewHolder {
        return QuizResultViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.quiz_result_last_users, parent, false))
    }

    override fun onBindViewHolder(holder: QuizResultViewHolder, position: Int) {
        val currentUser = lastUserList[position]
        holder.itemView.lastUser_name.text = currentUser.name
        holder.itemView.lastUser_hits.text = currentUser.hits.toString()
    }

    override fun getItemCount(): Int {
        return lastUserList.size    }

    fun setData(users: List<User>) {
        this.lastUserList = users
        notifyDataSetChanged()
    }

    class  QuizResultViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

}