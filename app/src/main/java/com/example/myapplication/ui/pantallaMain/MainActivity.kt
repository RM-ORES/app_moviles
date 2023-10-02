package com.example.myapplication.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.usecases.Add
import com.example.myapplication.domain.usecases.Delete
import com.example.myapplication.domain.usecases.Update
import com.example.myapplication.utils.StringProvider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    private lateinit var txt:EditText;
    private lateinit var boton: Button;

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            Add(),
            Update(),
            Delete()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        setContentView(R.layout.activity_main)

        txt = findViewById(R.id.editTextText)
        boton = findViewById(R.id.buttonAdd)
        boton.setOnClickListener{
            Toast.makeText(this,"${getString(R.string.toast_txt)} ${txt.text}", Toast.LENGTH_SHORT).show()
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