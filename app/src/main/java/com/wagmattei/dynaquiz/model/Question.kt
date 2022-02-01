package com.wagmattei.dynaquiz.model

data class Question(
    val id: Int,
    val statement: String,
    val options: List<String>
)
