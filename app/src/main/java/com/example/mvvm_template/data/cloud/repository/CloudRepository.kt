package com.example.mvvm_template.data.cloud.repository

import com.example.mvvm_template.data.cloud.ResultWrapper
import com.example.mvvm_template.data.cloud.rest.ApiService
import com.example.mvvm_template.data.cloud.safeApiCall
import com.example.mvvm_template.model.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CloudRepository(
    private val api: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): BaseCloudRepository {
    override suspend fun getCharacters(): ResultWrapper<ArrayList<Character>> {
        return safeApiCall(dispatcher){
            api.getCharacters()
        }
    }

    override suspend fun getCharacter(characterId: Int): ResultWrapper<ArrayList<Character>> {
        return safeApiCall(dispatcher){
            api.getCharacter(characterId)
        }
    }

}