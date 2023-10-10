package com.example.myapplication.data

import com.example.myapplication.domain.modelo.Sustancia
import com.example.myapplication.ui.pantallaMain.Constantes
import java.time.LocalDate

object Repository {
    private val sustancias = mutableListOf<Sustancia>()

    init {
        sustancias.add(
            Sustancia(
                Constantes.SUSNOMBRE1,
                LocalDate.of(2021, 6, 27),
                10,
                false,
                Constantes.ESTIMULANTE,
                8,
                3
            )
        )
        sustancias.add(
            Sustancia(
                Constantes.SUSNOMBRE2,
                LocalDate.of(2023, 7, 15),
                8,
                true,
                Constantes.ESTIMULANTE,
                6,
                3
            )
        )
        sustancias.add(
            Sustancia(
                Constantes.SUSNOMBRE3,
                LocalDate.of(2022, 5, 19),
                10,
                false,
                Constantes.PSICODELICO,
                9,
                5
            )
        )
        sustancias.add(
            Sustancia(
                Constantes.SUSNOMBRE4,
                LocalDate.of(2021, 8, 2),
                10,
                true,
                Constantes.DISOCIATIVO,
                7,
                1
            )
        )
        sustancias.add(
            Sustancia(
                Constantes.SUSNOMBRE5,
                LocalDate.of(2022, 9, 3),
                15,
                false,
                Constantes.ESTIMULANTE,
                7,
                3
            )
        )
        sustancias.add(
            Sustancia(
                Constantes.SUSNOMBRE6,
                LocalDate.of(2023, 7, 14),
                20,
                false,
                Constantes.PSICODELICO,
                9,
                4
            )
        )
        sustancias.add(
            Sustancia(
                Constantes.SUSNOMBRE7,
                LocalDate.of(2023, 8, 28),
                12,
                false,
                Constantes.ESTIMULANTE,
                8,
                5
            )
        )
    }

    fun getSustancia(index: Int): Sustancia? {
        if (sustancias.size < index || index < 0) {
            return null
        } else {
            return sustancias[index]
        }
    }

    fun sustanciasLength(): Int {
        return sustancias.size
    }

    fun addSustancia(sustancia: Sustancia) = sustancias.add(sustancia)
    fun updateSustancia(sustancia: Sustancia, index: Int) {
        sustancias.set(index, sustancia)
    }

    fun deleteSustancia(sustancia: Sustancia) = sustancias.remove(sustancia)
}