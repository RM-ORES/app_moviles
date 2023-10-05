package com.example.myapplication.data

import com.example.myapplication.domain.modelo.Sustancia
import java.time.LocalDate

object Repository {
    private val sustancias = mutableListOf<Sustancia>()

    init{
        sustancias.add(Sustancia("MDMA, pastilla gris punisher", LocalDate.now(),10,false,"Estimulante",8,3))
        sustancias.add(Sustancia("6-APB, polvo", LocalDate.now(),8,true,"Estimulante",6,3))
        sustancias.add(Sustancia("LSD, cartón", LocalDate.now(),10,false,"Psicodélico",9,5))
        sustancias.add(Sustancia("Cloretilo, spray", LocalDate.now(),10,true,"Disociativo",7,1))
        sustancias.add(Sustancia("Speed, polvo", LocalDate.now(),15,false,"Estimulante",7,3))
        sustancias.add(Sustancia("Setas Wollongong", LocalDate.now(),20,false,"Psicodélico",9,4))
    }

    fun getSustancia(index: Int): Sustancia? {
        if (sustancias.size < index || index < 0) {
            return null
        } else {
            return sustancias[index]
        }
    }

    fun addSustancia(sustancia: Sustancia) = sustancias.add(sustancia)
    fun updateSustancia(sustancia: Sustancia, index: Int) {
        sustancias.set(index, sustancia)
    }

    fun deleteSustancia(sustancia: Sustancia) = sustancias.remove(sustancia)
}