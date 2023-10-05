package com.example.myapplication.domain.modelo

import java.time.LocalDate
import java.util.Date

data class Sustancia(
    var descripcion:String = "",
    var fecha: LocalDate? =null,
    var precio: Int? =null,
    var legal:Boolean?=null,
    var efecto: String? =null,
    var potencia: Int? =null,
    var valoracion: Int? =null )