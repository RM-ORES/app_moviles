package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.modelo.Objeto

class Add {
    operator fun invoke(objeto: Objeto) =
        Repository.addObjeto(objeto)
}