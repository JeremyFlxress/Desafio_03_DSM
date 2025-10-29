package com.example.desafiopractico3.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopractico3.databinding.ItemRegionBinding // Importa el View Binding

class RegionAdapter : RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {

    // Lista de regiones (solo los nombres)
    private var regions: List<String> = emptyList()

    // Variable para manejar el clic en un item
    var onItemClick: ((String) -> Unit)? = null

    // 1. El ViewHolder (usa View Binding)
    inner class RegionViewHolder(val binding: ItemRegionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // Configuramos el clic
            binding.root.setOnClickListener {
                onItemClick?.invoke(regions[adapterPosition])
            }
        }

        fun bind(regionName: String) {
            binding.tvRegionName.text = regionName
        }
    }

    // 2. Crea el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        val binding = ItemRegionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RegionViewHolder(binding)
    }

    // 3. Conecta los datos con la vista
    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        holder.bind(regions[position])
    }

    // 4. Devuelve la cantidad de items
    override fun getItemCount(): Int {
        return regions.size
    }

    // Función para actualizar la lista de regiones desde la Activity
    fun submitList(newRegions: List<String>) {
        regions = newRegions
        notifyDataSetChanged() // Recarga la lista
    }
}