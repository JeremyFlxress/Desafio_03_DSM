package com.example.desafiopractico3.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiopractico3.controller.CountryListController
import com.example.desafiopractico3.databinding.ActivityCountryListBinding
import com.example.desafiopractico3.model.CountryResponse
import com.example.desafiopractico3.utils.NetworkUtils.isInternetAvailable
import kotlinx.coroutines.launch

class CountryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryListBinding
    private lateinit var countryAdapter: CountryAdapter
    private val countryListController = CountryListController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val region = intent.getStringExtra("region")
        if (region == null) {
            Toast.makeText(this, "Region not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        title = region

        setupRecyclerView()
        fetchCountries(region)
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCountries.layoutManager = LinearLayoutManager(this@CountryListActivity)
    }

    private fun fetchCountries(region: String) {
        if (!isInternetAvailable(this)) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
            return
        }
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                val countries = countryListController.getCountriesByRegion(region)
                countryAdapter = CountryAdapter(countries) { country ->
                    val intent = Intent(this@CountryListActivity, CountryDetailActivity::class.java)
                    intent.putExtra("country", country)
                    android.util.Log.d("CountryListActivity", "Starting CountryDetailActivity with country: ${country.name.common}")
                    startActivity(intent)
                }
                binding.recyclerViewCountries.adapter = countryAdapter
            } catch (e: Exception) {
                val errorMessage = com.example.desafiopractico3.utils.ErrorHandler.getErrorMessage(e)
                Toast.makeText(this@CountryListActivity, errorMessage, Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
