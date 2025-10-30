package com.example.desafiopractico3.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.desafiopractico3.controller.CountryDetailController
import com.example.desafiopractico3.databinding.ActivityCountryDetailBinding
import com.example.desafiopractico3.model.CountryResponse
import com.example.desafiopractico3.utils.NetworkUtils.isInternetAvailable
import kotlinx.coroutines.launch

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryDetailBinding
    private val apiKey = "b606774ec77d4d1e8d133554253010"
    private val countryDetailController = CountryDetailController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val country = intent.getParcelableExtra<CountryResponse>("country")
        if (country == null) {
            Toast.makeText(this, "Country not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        displayCountryDetails(country)
        fetchWeather(country.capital?.firstOrNull())
    }

    private fun displayCountryDetails(country: CountryResponse) {
        with(binding) {
            title = country.name.common
            Glide.with(this@CountryDetailActivity).load(country.flags.png).into(imageViewFlag)
            textViewOfficialName.text = country.name.official
            textViewCapital.text = "Capital: ${country.capital?.firstOrNull() ?: "N/A"}"
            textViewRegion.text = "Region: ${country.region}"
            textViewSubregion.text = "Subregion: ${country.subregion ?: "N/A"}"
            textViewPopulation.text = "Population: ${country.population}"
            textViewLanguages.text = "Languages: ${country.languages?.values?.joinToString() ?: "N/A"}"
            textViewCurrencies.text = "Currencies: ${country.currencies?.values?.joinToString { it.name } ?: "N/A"}"
            textViewLatLng.text = "Lat/Lng: ${country.latlng?.joinToString() ?: "N/A"}"
        }
    }

    private fun fetchWeather(capital: String?) {
        if (capital == null) {
            binding.textViewWeatherTitle.text = "Weather data not available"
            return
        }

        if (!isInternetAvailable(this)) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
            binding.weatherProgressBar.visibility = View.GONE
            return
        }

        binding.weatherProgressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                val weatherResponse = countryDetailController.getWeatherByCapital(apiKey, capital)
                with(binding) {
                    Glide.with(this@CountryDetailActivity).load("https:${weatherResponse.current.condition.icon}").into(imageViewWeatherIcon)
                    textViewTemperature.text = "${weatherResponse.current.tempC}°C"
                    textViewWeatherCondition.text = weatherResponse.current.condition.text
                    textViewWind.text = "Wind: ${weatherResponse.current.windKph} kph"
                    textViewHumidity.text = "Humidity: ${weatherResponse.current.humidity}%"
                }
            } catch (e: Exception) {
                binding.textViewWeatherTitle.text = "Could not fetch weather data"
                val errorMessage = com.example.desafiopractico3.utils.ErrorHandler.getErrorMessage(e)
                Toast.makeText(this@CountryDetailActivity, errorMessage, Toast.LENGTH_LONG).show()
            } finally {
                binding.weatherProgressBar.visibility = View.GONE
            }
        }
    }
}
