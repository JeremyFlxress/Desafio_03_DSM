package com.example.desafiopractico3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.desafiopractico3.databinding.ActivityMainBinding // Importante
import com.example.desafiopractico3.network.RetrofitClient
import com.example.desafiopractico3.view.RegionAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // 1. Configurar View Binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RegionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 2. Inflar el layout usando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Inicializar el Adapter y el RecyclerView
        setupRecyclerView()

        // 4. Cargar los datos de la API
        loadRegions()

        // 5. Configurar el ícono de la app (Requisito)
        // Ve a res/mipmap, clic derecho > New > Image Asset y sigue el asistente.
        // El desafío pide que lo personalices[cite: 1827].
    }

    private fun setupRecyclerView() {
        adapter = RegionAdapter()
        binding.rvRegions.adapter = adapter

        // Configurar qué pasa cuando se hace clic en un item
        adapter.onItemClick = { regionName ->
            // Aquí irá la lógica para abrir la Pantalla 2
            Toast.makeText(this, "Región seleccionada: $regionName", Toast.LENGTH_SHORT).show()

            // Ejemplo de cómo tu compañero (Parte 2) recibirá el dato:
            // val intent = Intent(this, CountryListActivity::class.java)
            // intent.putExtra("REGION_NAME", regionName)
            // startActivity(intent)
        }
    }

    private fun loadRegions() {
        // Usamos corutinas (Guía 13) para la llamada de red
        lifecycleScope.launch{
            try {
                // Mostrar el ProgressBar
                binding.progressBar.visibility = View.VISIBLE

                // 1. LLAMAR A LA API
                val countries = RetrofitClient.apiService.getAllCountries()

                // 2. PROCESAR DATOS (¡Tu lógica principal!)
                // Mapeamos la lista de países a una lista de sus regiones,
                // filtramos para obtener solo las únicas (distinct), y las ordenamos.
                val regions = countries
                    .map { it.region }
                    .distinct()
                    .sorted()

                // 3. MOSTRAR DATOS
                adapter.submitList(regions)

            } catch (e: Exception) {
                // MANEJO DE ERRORES (Requisito)
                Toast.makeText(
                    this@MainActivity,
                    "Error al cargar regiones: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            } finally {
                // Ocultar el ProgressBar
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}