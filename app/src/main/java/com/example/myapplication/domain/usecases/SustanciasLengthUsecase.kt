package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository

class SustanciasLengthUsecase {
    operator fun invoke(): Int? {
        return Repository.sustanciasLength()
    }
}