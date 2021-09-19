package com.example.mvvm_template.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.mvvm_template.core.BaseViewModel
import com.example.mvvm_template.data.cloud.ResultWrapper
import com.example.mvvm_template.data.cloud.repository.BaseCloudRepository
import com.example.mvvm_template.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {
    val characters = MutableLiveData(ArrayList<Character>())
    val loading = MutableLiveData(false)
    fun getCharacters(){
        launchIO {
            loading.postValue(true)
            when(val wrapper = baseCloudRepository.getCharacters()){
                is ResultWrapper.Success -> {
                    characters.postValue(wrapper.value)
                }
                is ResultWrapper.Error -> {

                }
            }
            loading.postValue(false)
        }
    }

}