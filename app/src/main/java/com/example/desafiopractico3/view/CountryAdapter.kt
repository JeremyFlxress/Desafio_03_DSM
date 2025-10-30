package com.example.desafiopractico3.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiopractico3.databinding.ItemCountryBinding
import com.example.desafiopractico3.model.CountryResponse

class CountryAdapter(
    private var countries: List<CountryResponse>,
    private val onItemClick: (CountryResponse) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(countries[adapterPosition])
            }
        }

        fun bind(country: CountryResponse) {
            binding.textViewCountryName.text = country.name.common
            binding.textViewCapital.text = country.capital?.firstOrNull()
            Glide.with(binding.root.context).load(country.flags.png).into(binding.imageViewFlag)
        }
    }
}
