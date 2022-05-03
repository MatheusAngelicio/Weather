package com.matheus.weather.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.matheus.weather.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.weather.observe(this) {
            Toast.makeText(this, "${it?.name}", Toast.LENGTH_SHORT).show()
        }

        viewModel.error.observe(this) {
            /*if (it) Toast.makeText(this, "Deu Erro", Toast.LENGTH_SHORT).show() else Toast.makeText(
                this,
                "Deu certo",
                Toast.LENGTH_SHORT
            ).show()*/

        }

    }
}