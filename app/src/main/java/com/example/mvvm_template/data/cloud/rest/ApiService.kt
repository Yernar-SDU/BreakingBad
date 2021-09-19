package com.example.mvvm_template.data.cloud.rest

import com.example.mvvm_template.model.Character
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("characters")
    suspend fun getCharacters(): ArrayList<Character>

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") character_id: Int): ArrayList<Character>


}