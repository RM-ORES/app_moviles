package com.example.myapplication.ui.pantallaMain

import com.example.myapplication.domain.modelo.Objeto
import java.lang.Error

data class MainState (
val objeto:Objeto= Objeto(null), val error: String? = null

    )