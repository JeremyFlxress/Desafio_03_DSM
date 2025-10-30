package com.example.desafiopractico3.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopractico3.databinding.ItemRegionBinding // Importa el View Binding

class RegionAdapter(
    private val regions: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {


    // 1. El ViewHolder (usa View Binding)
    inner class RegionViewHolder(val binding: ItemRegionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(regions[adapterPosition])
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

}