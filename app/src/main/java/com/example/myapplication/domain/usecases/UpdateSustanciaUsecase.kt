package com.example.myapplication.domain.usecases


import com.example.myapplication.data.Repository
import com.example.myapplication.domain.modelo.Sustancia

class UpdateSustanciaUsecase {
    operator fun invoke(sustancia: Sustancia, index:Int) = Repository.updateSustancia(sustancia, index)
}