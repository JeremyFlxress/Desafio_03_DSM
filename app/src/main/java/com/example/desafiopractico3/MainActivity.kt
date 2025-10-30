package com.example.desafiopractico3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiopractico3.controller.MainController
import com.example.desafiopractico3.databinding.ActivityMainBinding
import com.example.desafiopractico3.view.CountryListActivity
import com.example.desafiopractico3.utils.NetworkUtils.isInternetAvailable
import com.example.desafiopractico3.view.RegionAdapter
import com.example.desafiopractico3.utils.ErrorHandler.getErrorMessage
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainController = MainController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRegions()
    }

    private fun setupRecyclerView(regions: List<String>) {
        val adapter = RegionAdapter(regions) { regionName ->
            val intent = Intent(this, CountryListActivity::class.java)
            intent.putExtra("region", regionName)
            startActivity(intent)
        }
        binding.rvRegions.layoutManager = LinearLayoutManager(this)
        binding.rvRegions.adapter = adapter
    }

    private fun loadRegions() {
        if (!isInternetAvailable(this)) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
            return
        }
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                val regions = mainController.getRegions()
                setupRecyclerView(regions)

            } catch (e: Exception) {
                val errorMessage = getErrorMessage(e)
                Toast.makeText(
                    this@MainActivity,
                    errorMessage,
                    Toast.LENGTH_LONG
                ).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}