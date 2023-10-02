package com.example.myapplication.data

import com.example.myapplication.domain.modelo.Objeto

object Repository {
    private val objetos = mutableListOf<Objeto>()

    init{

    }
    private val mapObjetos = mutableMapOf<String, Objeto>()

    fun getObjetos(): List<Objeto> {return objetos}
    fun findObjeto(id:Int): Objeto? {
        return objetos.find { objeto: Objeto -> objeto.id == id }
    }
    fun addObjeto(objeto: Objeto) = objetos.add(objeto)
    fun updateObjeto(objeto: Objeto) {
        objetos.remove(findObjeto(objeto.id))
        objetos.add(objeto)
    }
    fun deleteObjeto(objeto: Objeto) = objetos.remove(objeto)
}