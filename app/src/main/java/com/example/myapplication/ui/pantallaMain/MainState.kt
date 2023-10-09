package com.example.myapplication.ui.pantallaMain

import com.example.myapplication.domain.modelo.Sustancia

data class MainState(
    val sustancia: Sustancia = Sustancia(),
    val error: String? = null
)