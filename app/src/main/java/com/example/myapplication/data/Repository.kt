package com.example.myapplication.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.domain.modelo.Sustancia
import java.time.Instant
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
object Repository {
    private val sustancias = mutableListOf<Sustancia>()

    init{
        sustancias.add(Sustancia("MDMA, pastilla gris punisher", Date.from(Instant.now()),10,false,"Estimulante",8,3))
        sustancias.add(Sustancia("6-APB, polvo", Date.from(Instant.now()),8,true,"Estimulante",6,3))
        sustancias.add(Sustancia("LSD, cartón", Date.from(Instant.now()),10,false,"Psicodélico",9,5))
        sustancias.add(Sustancia("Cloretilo, spray", Date.from(Instant.now()),10,true,"Disociativo",7,1))
        sustancias.add(Sustancia("Speed, polvo", Date.from(Instant.now()),15,false,"Estimulante",7,3))
        sustancias.add(Sustancia("Setas Wollongong", Date.from(Instant.now()),20,false,"Psicodélico",9,4))
    }
    private val mapSustancia = mutableMapOf<String, Sustancia>()

    fun findSustancia(index: Int): Sustancia? {return sustancias[index]}
    fun addSustancia(sustancia: Sustancia) = sustancias.add(sustancia)
    fun updateSustancia(sustancia: Sustancia, index: Int) {
        sustancias[index] = sustancia
    }
    fun deleteSustancia(sustancia: Sustancia) = sustancias.remove(sustancia)
}