package com.example.myapplication.ui.pantallaMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    private lateinit var txt:EditText;
    private lateinit var boton: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        setContentView(R.layout.activity_main)

        txt = findViewById(R.id.editTextText)
        boton = findViewById(R.id.button)
        boton.setOnClickListener{
            Toast.makeText(this,"${getString(R.string.toast_txt)} ${txt.text}", Toast.LENGTH_SHORT).show()
        }

    }
}