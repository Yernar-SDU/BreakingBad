package com.example.mvvm_template.ui.character_details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_template.core.BaseViewModel
import com.example.mvvm_template.data.cloud.ResultWrapper
import com.example.mvvm_template.data.cloud.repository.BaseCloudRepository
import com.example.mvvm_template.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val baseCloudRepository: BaseCloudRepository) :
    BaseViewModel() {
    private val TAG = this::class.java.simpleName
    val character = MutableLiveData(Character())

    fun getCharacter(char_id: Int){
        launchIO {
            when(val wrapper = baseCloudRepository.getCharacter(char_id)){
                is ResultWrapper.Success -> {
                    character.postValue(wrapper.value[0])
                }
                is ResultWrapper.Error -> {
                    Log.i(TAG, "getCharacter: ${wrapper.error}")
                }
            }
        }
    }


}