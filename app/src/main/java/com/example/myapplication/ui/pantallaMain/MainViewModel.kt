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
import java.time.LocalDate

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
        _uiState.value = MainState(
            sustancia = getSustanciaUsecase(index)!!,
            error = null,
            principio = true,
            fin = false
        )
    }

    fun next() {
        _uiState.value = _uiState.value?.copy(fin = false)
        index += 1
        getSustancia()
    }

    fun previous() {
        _uiState.value = _uiState.value?.copy(principio = false)
        index -= 1
        getSustancia()
    }

    fun addSustancia(sustancia: Sustancia) {
        addSustanciaUsecase(sustancia)
        _uiState.value = _uiState.value?.copy(error = Constantes.ANADIDO, fin = false)
        getSustancia()
    }

    fun deleteSustancia(sustancia: Sustancia) {
        deleteSustanciaUsecase(sustancia)
        _uiState.value = _uiState.value?.copy(error = Constantes.BORRADO)
        if (sustanciasLengthUsecase.invoke() == 0) {
            _uiState.value = MainState(
                sustancia = Sustancia("", LocalDate.now(), 0, false, null, 0, 0),
                error = null,
                principio = true,
                fin = true
            )
            addSustancia(_uiState.value!!.sustancia)
        }
        if (_uiState.value?.fin == true && index > 0) {
            index -= 1
        }
        getSustancia()
    }

    fun updateSustancia(sustancia: Sustancia) {
        updateSustanciaUsecase(sustancia, index)
        _uiState.value = _uiState.value?.copy(error = Constantes.MODIFICADO)
        getSustancia()
    }

    private fun getSustancia() {
        if (getSustanciaUsecase(index) == null) {
            _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
        } else {
            _uiState.value = _uiState.value?.copy(
                sustancia = getSustanciaUsecase(index)!!,
                principio = index == 0,
                fin = index == sustanciasLengthUsecase.invoke() - 1
            )
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