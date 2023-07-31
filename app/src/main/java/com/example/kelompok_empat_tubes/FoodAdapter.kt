package com.example.kelompok_empat_tubes

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kelompok_empat_tubes.databinding.ItemLayoutBinding

class FoodAdapter (private val foodList:ArrayList<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){
    class FoodViewHolder (val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return FoodViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.binding.nama.text = foodList[position].nama
        holder.binding.harga.text = foodList[position].harga
        holder.binding.cardView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailProduct::class.java)
            intent.putExtra("nama", foodList[position].nama)
            intent.putExtra("harga", foodList[position].harga)
            intent.putExtra("isi", foodList[position].isi)
            intent.putExtra("gambar", foodList[position].gambar)
            holder.itemView.context.startActivity(intent)
        }
        Glide.with(holder.itemView.context).load(foodList[position].gambar).into(holder.binding.gambar)

    }

    fun setData(newData: ArrayList<Food>) {
        foodList.clear()
        foodList.addAll(newData)
        notifyDataSetChanged()
    }

}