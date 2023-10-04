package com.example.myapplication.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.usecases.AddSustancia
import com.example.myapplication.domain.usecases.DeleteSustancia
import com.example.myapplication.domain.usecases.UpdateSustancia
import com.example.myapplication.utils.StringProvider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;


    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            AddSustancia(),
            UpdateSustancia(),
            DeleteSustancia()
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

        }
    }
    private fun eventos(){//listeners
        with(binding){

        }
    }
}