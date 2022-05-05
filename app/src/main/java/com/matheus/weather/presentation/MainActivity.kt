package com.matheus.weather.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.matheus.weather.R
import com.matheus.weather.data.WeatherData
import com.matheus.weather.databinding.ActivityMainBinding
import com.matheus.weather.util.Location
import com.matheus.weather.util.convertFahrenheitToCelcius
import com.matheus.weather.util.formatSunriseSunset
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getLatLon()
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.weather.observe(this) {
            initViews(it)
        }

        viewModel.isLoading.observe(this) {
            binding.loader.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) {
            /*if (it) Toast.makeText(this, "Deu Erro", Toast.LENGTH_SHORT).show() else Toast.makeText(
                this,
                "Deu certo",
                Toast.LENGTH_SHORT
            ).show()*/

        }

    }

    private fun getLatLon(){
        val location = Location(this)
        location.run {
            fetchLocation()
            onAddressListener = {
                viewModel.setupLatLon(it.first, it.second)
            }
        }
    }

    private fun initViews(weather: WeatherData?) {
        weather?.let {
            binding.apply {
                address.text = getString(R.string.address, weather.name, weather.sys?.country ?: "---")
                updateAt.text = formatUpdateAtText(weather)
                if (!weather.weather.isNullOrEmpty()) status.text = weather.weather[0].description.toString()
                temp.text = getString(R.string.formated_temp, convertFahrenheitToCelcius(weather.main?.temp).substring(0,2))
                tempMin.text = getString(R.string.formated_temp_min, convertFahrenheitToCelcius(weather.main?.tempMin).substring(0,2))
                tempMax.text = getString(R.string.formated_temp_max, convertFahrenheitToCelcius(weather.main?.tempMax).substring(0,2))
                sunrise.text = formatSunriseSunset(weather.sys?.sunrise)
                sunset.text = formatSunriseSunset(weather.sys?.sunset)
                wind.text = weather.wind?.speed.toString()
                pressure.text = weather.main?.pressure.toString()
                humidity.text = weather.main?.humidy.toString()

                mainContainer.visibility = View.VISIBLE
            }
        }
    }

    private fun formatUpdateAtText(weather: WeatherData): String {
        val updatedAtWt = weather.dt
        updatedAtWt?.let {
            return getString(
                R.string.update_at,
                SimpleDateFormat(
                    "dd/MM/yyyy hh:mm a",
                    Locale.ENGLISH
                ).format(Date(updatedAtWt * 1000))
            )
        }
        return "---"
    }



}