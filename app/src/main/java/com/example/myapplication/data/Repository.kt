package com.example.myapplication.data

import com.example.myapplication.domain.modelo.Pastilla

object Repository {
    private val pastillas = mutableListOf<Pastilla>()

    init{

    }
    private val mapObjetos = mutableMapOf<String, Pastilla>()

    fun getObjetos(): List<Pastilla> {return pastillas}
    fun findObjeto(id:Int): Pastilla? {
        return pastillas.find { pastilla: Pastilla -> pastilla.id == id }
    }
    fun addObjeto(pastilla: Pastilla) = pastillas.add(pastilla)
    fun updateObjeto(pastilla: Pastilla) {
        pastillas.remove(findObjeto(pastilla.id))
        pastillas.add(pastilla)
    }
    fun deleteObjeto(pastilla: Pastilla) = pastillas.remove(pastilla)
}