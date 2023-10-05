package com.example.myapplication.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.modelo.Sustancia
import com.example.myapplication.domain.usecases.AddSustanciaUsecase
import com.example.myapplication.domain.usecases.DeleteSustanciaUsecase
import com.example.myapplication.domain.usecases.GetSustanciaUsecase
import com.example.myapplication.domain.usecases.UpdateSustanciaUsecase
import com.example.myapplication.utils.StringProvider

class MainViewModel(
    private val stringProvider:StringProvider,
    private val addSustanciaUsecase: AddSustanciaUsecase,
    private val deleteSustanciaUsecase: DeleteSustanciaUsecase,
    private val updateSustanciaUsecase: UpdateSustanciaUsecase,
    private val getSustanciaUsecase: GetSustanciaUsecase,
) : ViewModel(){

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState
    private var index = 0

    init {
        this.getSustancia()
    }
    fun next(){
        if (getSustanciaUsecase(index + 1) == null){
            _uiState.value = _uiState.value?.copy(error = Constantes.ERRORNEXT)
        } else {
            index += 1
            getSustancia()
        }

    }
    fun previous(){
        if (index-1 < 0){
            _uiState.value = _uiState.value?.copy(error = Constantes.ERRORPREVIOUS)
        } else {
            index -= 1
            getSustancia()
        }

    }
    fun addSustancia(sustancia: Sustancia){
        if(!addSustanciaUsecase(sustancia)){
            _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
        } else {
           _uiState.value = _uiState.value?.copy(error = Constantes.AÑADIDO)
        }
    }
    fun deleteSustancia(sustancia: Sustancia){
        if(!deleteSustanciaUsecase(sustancia)){
            _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
        } else{
            _uiState.value = _uiState.value?.copy(error = Constantes.BORRADO)
        }
    }
    fun updateSustancia(sustancia: Sustancia){
        updateSustanciaUsecase(sustancia,index)
    }
    fun getSustancia(){
        if (getSustanciaUsecase(index) == null){
            _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
        } else {
            _uiState.value = MainState(sustancia = getSustanciaUsecase(index)!!)
        }
    }
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
    private val getSustanciaUsecase: GetSustanciaUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                stringProvider,
                addSustanciaUsecase,
                deleteSustanciaUsecase,
                updateSustanciaUsecase,
                getSustanciaUsecase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}