package com.example.myapplication.ui.pantallaMain

import com.example.myapplication.domain.modelo.Pastilla

data class MainState (
    val pastilla:Pastilla= Pastilla(null), val error: String? = null

    )