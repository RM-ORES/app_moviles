package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.modelo.Sustancia

class GetSustanciaUsecase {
    operator fun invoke(index: Int): Sustancia? {
        return Repository.getSustancia(index)
    }
}