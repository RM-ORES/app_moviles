package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.modelo.Sustancia

class DeleteSustancia {
    operator fun invoke(sustancia: Sustancia) = Repository.deleteSustancia(sustancia)
}