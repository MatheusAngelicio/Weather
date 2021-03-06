package com.matheus.weather.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.matheus.weather.R
import com.matheus.weather.data.WeatherData
import com.matheus.weather.databinding.ActivityMainBinding
import com.matheus.weather.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel
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

    override fun onResume() {
        super.onResume()
        getLatLon()
    }

    private fun observeViewModel() {
        viewModel.weather.observe(this) {
            initViews(it)
        }

        viewModel.isLoading.observe(this) {
            binding.apply {
                if (it) loader.visible() else loader.gone()
            }
        }

        viewModel.error.observe(this) {
            if (it) showDialogError()
        }
    }

    private fun getLatLon() {
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
                address.text =
                    getString(R.string.address, weather.name, weather.sys?.country ?: "---")
                updateAt.text = formatUpdateAtText(weather.dt, this@MainActivity)
                if (!weather.weather.isNullOrEmpty()) status.text =
                    weather.weather[0].description.toString()
                temp.text = getString(
                    R.string.formated_temp,
                    convertFahrenheitToCelcius(weather.main?.temp).substring(0, 2)
                )
                tempMin.text = getString(
                    R.string.formated_temp_min,
                    convertFahrenheitToCelcius(weather.main?.tempMin).substring(0, 2)
                )
                tempMax.text = getString(
                    R.string.formated_temp_max,
                    convertFahrenheitToCelcius(weather.main?.tempMax).substring(0, 2)
                )
                sunrise.text = formatNumberToHourMinute(weather.sys?.sunrise)
                sunset.text = formatNumberToHourMinute(weather.sys?.sunset)
                wind.text = weather.wind?.speed.toString()
                pressure.text = weather.main?.pressure.toString()
                humidity.text = weather.main?.humidy.toString()

                mainContainer.visibility = View.VISIBLE
            }
        }
    }

    private fun showDialogError() {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_dialog_title_error)
            .setMessage(R.string.messageToShow)
            .setPositiveButton(R.string.understood) { _, _ -> }
            .show()
    }

}