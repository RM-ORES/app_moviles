package com.example.myapplication.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.modelo.Sustancia
import com.example.myapplication.domain.usecases.AddSustanciaUsecase
import com.example.myapplication.domain.usecases.DeleteSustanciaUsecase
import com.example.myapplication.domain.usecases.FindSustanciaUsecase
import com.example.myapplication.domain.usecases.UpdateSustanciaUsecase
import com.example.myapplication.utils.StringProvider

class MainViewModel(
    private val stringProvider:StringProvider,
    private val addSustanciaUsecase: AddSustanciaUsecase,
    private val deleteSustanciaUsecase: DeleteSustanciaUsecase,
    private val updateSustanciaUsecase: UpdateSustanciaUsecase,
    private val findSustanciaUsecase: FindSustanciaUsecase
) : ViewModel(){

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    fun addSustancia(sustancia: Sustancia){
        addSustancia(sustancia)
//        if(!addSustancia(sustancia)){
//            _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
//        }
    }
    fun deleteSustancia(sustancia: Sustancia){}
    fun updateSustancia(sustancia: Sustancia){}
    fun findSustancia(sustancia: Sustancia){}

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val stringProvider:StringProvider,
    private val addSustanciaUsecase: AddSustanciaUsecase,
    private val deleteSustanciaUsecase: DeleteSustanciaUsecase,
    private val updateSustanciaUsecase: UpdateSustanciaUsecase,
    private val findSustanciaUsecase: FindSustanciaUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                stringProvider,
                addSustanciaUsecase,
                deleteSustanciaUsecase,
                updateSustanciaUsecase,
                findSustanciaUsecase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}