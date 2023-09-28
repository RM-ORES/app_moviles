package com.example.myapplication.ui.pantallaMain.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(

) : ViewModel(){
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState
}
/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}