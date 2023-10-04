package com.example.myapplication.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.modelo.Sustancia
import com.example.myapplication.domain.usecases.AddSustanciaUsecase
import com.example.myapplication.domain.usecases.DeleteSustanciaUsecase
import com.example.myapplication.domain.usecases.FindSustanciaUsecase
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
            FindSustanciaUsecase()
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

        }
    }

    private fun eventos() {//listeners
        with(binding) {
            buttonAdd.setOnClickListener {
                var efecto: String =
                    radioGroup.findViewById<RadioButton?>(radioGroup.checkedRadioButtonId).text.toString()

                viewModel.addSustancia(
                    Sustancia(
                        enterDescripcion.text.toString(),
                        LocalDate.parse(enterDate.text),
                        enterNumber.text.toString().toFloat(),
                        checkBox.isChecked,
                        efecto,
                        seekBar.progress,
                        ratingBar.numStars
                    )
                )
            }
        }
    }
}