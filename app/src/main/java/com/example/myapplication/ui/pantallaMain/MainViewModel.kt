package com.example.myapplication.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.modelo.Sustancia
import com.example.myapplication.domain.usecases.AddSustanciaUsecase
import com.example.myapplication.domain.usecases.DeleteSustanciaUsecase
import com.example.myapplication.domain.usecases.GetSustanciaUsecase
import com.example.myapplication.domain.usecases.SustanciasLengthUsecase
import com.example.myapplication.domain.usecases.UpdateSustanciaUsecase

class MainViewModel(
    private val addSustanciaUsecase: AddSustanciaUsecase,
    private val deleteSustanciaUsecase: DeleteSustanciaUsecase,
    private val updateSustanciaUsecase: UpdateSustanciaUsecase,
    private val getSustanciaUsecase: GetSustanciaUsecase,
    private val sustanciasLengthUsecase: SustanciasLengthUsecase
) : ViewModel() {

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState
    private var index = 0

    init {
        this.getSustancia()
    }

    fun next() {
        if (index + 1 >= sustanciasLengthUsecase.invoke()!!) {
            _uiState.value = _uiState.value?.copy(error = Constantes.ERRORNEXT)
        } else {
            index += 1
            getSustancia()
        }

    }

    fun previous() {
        if (index - 1 < 0) {
            _uiState.value = _uiState.value?.copy(error = Constantes.ERRORPREVIOUS)
        } else {
            index -= 1
            getSustancia()
        }
    }

    fun addSustancia(sustancia: Sustancia) {
        addSustanciaUsecase(sustancia)
        _uiState.value = _uiState.value?.copy(error = Constantes.AÑADIDO)
        getSustancia()
    }

    fun deleteSustancia(sustancia: Sustancia) {
        deleteSustanciaUsecase(sustancia)
        _uiState.value = _uiState.value?.copy(error = Constantes.BORRADO)
    }

    fun updateSustancia(sustancia: Sustancia) {
        updateSustanciaUsecase(sustancia, index)
        _uiState.value = _uiState.value?.copy(error = Constantes.MODIFICADO)
    }

    fun getSustancia() {
        if (getSustanciaUsecase(index) == null) {
            _uiState.value = MainState(sustancia = Sustancia(), error = Constantes.ERROR)
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
    private val addSustanciaUsecase: AddSustanciaUsecase,
    private val deleteSustanciaUsecase: DeleteSustanciaUsecase,
    private val updateSustanciaUsecase: UpdateSustanciaUsecase,
    private val getSustanciaUsecase: GetSustanciaUsecase,
    private val sustanciasLengthUsecase: SustanciasLengthUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                addSustanciaUsecase,
                deleteSustanciaUsecase,
                updateSustanciaUsecase,
                getSustanciaUsecase,
                sustanciasLengthUsecase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}