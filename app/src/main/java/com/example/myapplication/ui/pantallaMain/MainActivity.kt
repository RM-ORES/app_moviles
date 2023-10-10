package com.example.myapplication.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.modelo.Sustancia
import com.example.myapplication.domain.usecases.AddSustanciaUsecase
import com.example.myapplication.domain.usecases.DeleteSustanciaUsecase
import com.example.myapplication.domain.usecases.GetSustanciaUsecase
import com.example.myapplication.domain.usecases.SustanciasLengthUsecase
import com.example.myapplication.domain.usecases.UpdateSustanciaUsecase
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            AddSustanciaUsecase(),
            DeleteSustanciaUsecase(),
            UpdateSustanciaUsecase(),
            GetSustanciaUsecase(),
            SustanciasLengthUsecase()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        observarViewModel()
        eventos()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            state.error?.let { error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }
            binding.apply {
                if (state.error == null) {
                    val sustancia = viewModel.uiState.value?.sustancia

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
                        ratingBar.rating = sustancia.valoracion!!.toFloat()
                    }
                }

                if (state.fin == true) {
                    buttonNext.isEnabled = false
                    buttonNext.visibility = View.INVISIBLE
                } else {
                    buttonNext.isEnabled = true
                    buttonNext.visibility = View.VISIBLE
                }
                if (state.principio == true) {
                    buttonPrevious.isEnabled = false
                    buttonPrevious.visibility = View.INVISIBLE
                } else {
                    buttonPrevious.isEnabled = true
                    buttonPrevious.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun eventos() {
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