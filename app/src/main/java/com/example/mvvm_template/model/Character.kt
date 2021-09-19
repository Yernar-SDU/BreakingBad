package com.example.mvvm_template.model

data class Character(
    val char_id: Int = 0,
    val name: String = "",
    val birthday: String = "",
    val occupation: ArrayList<String> = arrayListOf(),
    val img: String = "",
    val status: String = "",
    val nickname: String = "",
    val appearance: ArrayList<Int> = arrayListOf(),
    val portrayed: String = "",
    val category: String ="",
    val better_call_saul_appearance: ArrayList<Int> = arrayListOf()
)