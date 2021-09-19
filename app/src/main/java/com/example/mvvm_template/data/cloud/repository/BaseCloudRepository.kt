package com.example.mvvm_template.data.cloud.repository

import com.example.mvvm_template.data.cloud.ResultWrapper
import com.example.mvvm_template.model.Character


interface BaseCloudRepository{

    suspend fun getCharacters(): ResultWrapper<ArrayList<Character>>

    suspend fun getCharacter(characterId: Int): ResultWrapper<ArrayList<Character>>

}