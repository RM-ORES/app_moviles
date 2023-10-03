package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.modelo.Pastilla

class Add {
    operator fun invoke(pastilla: Pastilla) =
        Repository.addObjeto(pastilla)
}