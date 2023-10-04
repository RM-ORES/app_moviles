package com.example.myapplication.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplication.data.Repository
import com.example.myapplication.domain.modelo.Sustancia

class UpdateSustanciaUsecase {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(sustancia: Sustancia, index:Int) = Repository.updateSustancia(sustancia, index)
}