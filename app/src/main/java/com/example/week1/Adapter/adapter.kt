package com.example.week1.Adapter

import Interface.cardListener
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week1.R
import com.example.week1.databinding.AnimalcardsBinding
import com.example.week1.model.animals


class recycleAdapter(val listAnimals : ArrayList<animals>, val cardListener: cardListener) :
    RecyclerView.Adapter<recycleAdapter.viewHolder>() {



    class viewHolder(val itemView: View, val cardListener: cardListener) :
        RecyclerView.ViewHolder(itemView) {

        val binding = AnimalcardsBinding.bind(itemView)

        fun setData(animals: animals) {
            binding.animalNameCard.text = animals.name
            binding.animalTypeCards.text = animals.animalType
            if (animals.imageUri.isNotEmpty()) {
                binding.imageView3.setImageURI(Uri.parse(animals.imageUri))
            }
            itemView.setOnClickListener {
                cardListener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.animalcards, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, state: Int) {
        holder.setData(listAnimals[state])
    }

    override fun getItemCount(): Int {
        return listAnimals.size
    }
}