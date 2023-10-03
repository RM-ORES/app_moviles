package com.example.myapplication.domain.usecases

import com.example.myapplication.data.Repository
import com.example.myapplication.domain.modelo.Pastilla

class Update {
    operator fun invoke(pastilla: Pastilla) = Repository.updateObjeto(pastilla)
}