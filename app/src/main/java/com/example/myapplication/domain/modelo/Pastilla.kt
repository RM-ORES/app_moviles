package com.example.myapplication.domain.modelo

import java.util.Date

data class Pastilla(
    var descripcion:String = "",
    var fecha: Date? =null,
    var precio: Int? =null,
    var legal:Boolean?=null,
    var efecto: String? =null,
    var potencia: Int? =null,
    var valoracion: Int? =null )