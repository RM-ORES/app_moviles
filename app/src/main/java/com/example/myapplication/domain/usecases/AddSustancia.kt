package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.modelo.Sustancia

class AddSustancia {
    operator fun invoke(sustancia: Sustancia) =
        Repository.addSustancia(sustancia)
}