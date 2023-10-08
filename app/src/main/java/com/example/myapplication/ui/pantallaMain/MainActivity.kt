package com.example.myapplication.ui.pantallaMain

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.modelo.Sustancia
import com.example.myapplication.domain.usecases.AddSustanciaUsecase
import com.example.myapplication.domain.usecases.DeleteSustanciaUsecase
import com.example.myapplication.domain.usecases.GetSustanciaUsecase
import com.example.myapplication.domain.usecases.UpdateSustanciaUsecase
import com.example.myapplication.utils.StringProvider
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            AddSustanciaUsecase(),
            DeleteSustanciaUsecase(),
            UpdateSustanciaUsecase(),
            GetSustanciaUsecase()
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        viewModel.getSustancia()
        observarViewModel()
        eventos()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state.error?.let { error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }
            if (state.error == null){
                binding.apply {
                    var sustancia = viewModel.uiState.value?.sustancia

                    enterDescripcion.setText(sustancia!!.descripcion)
                    enterDate.setText(sustancia.fecha.toString())
                    enterNumber.setText(sustancia.precio.toString())
                    checkBox.isChecked = sustancia.legal!!
                    when (sustancia.efecto) {
                        Constantes.ESTIMULANTE -> radioButton1.isChecked = true
                        Constantes.DISOCIATIVO -> radioButton2.isChecked = true
                        Constantes.PSICODELICO -> radioButton3.isChecked = true
                    }
                    if (sustancia.potencia == null) {
                        seekBar.progress = 0
                    } else {
                        seekBar.progress = sustancia.potencia!!
                    }
                    if (sustancia.valoracion == null) {
                        ratingBar.numStars = 0
                    } else {
                        //ratingBar.numStars = sustancia.valoracion!!
                        ratingBar.rating = sustancia.valoracion!!.toFloat()
                    }
                }
            }

        }
    }

    private fun eventos() {//listeners
        with(binding) {
            buttonNext.setOnClickListener {
                viewModel.next()
            }

            buttonPrevious.setOnClickListener {
                viewModel.previous()
            }

            buttonAdd.setOnClickListener {
                viewModel.addSustancia(
                    Sustancia(
                        enterDescripcion.text.toString(),
                        LocalDate.parse(enterDate.text),
                        enterNumber.text.toString().toInt(),
                        checkBox.isChecked,
                        radioGroup.findViewById<RadioButton?>(radioGroup.checkedRadioButtonId).text.toString(),
                        seekBar.progress,
                        ratingBar.rating.toInt()
                    )
                )
            }
            buttonDelete.setOnClickListener {
                viewModel.uiState.value?.let { it1 -> viewModel.deleteSustancia(it1.sustancia) }
            }

            buttonUpdate.setOnClickListener {
                viewModel.updateSustancia(
                    Sustancia(
                        enterDescripcion.text.toString(),
                        LocalDate.parse(enterDate.text),
                        enterNumber.text.toString().toInt(),
                        checkBox.isChecked,
                        radioGroup.findViewById<RadioButton?>(radioGroup.checkedRadioButtonId).text.toString(),
                        seekBar.progress,
                        ratingBar.rating.toInt()
                    )
                )
            }
        }
    }
}