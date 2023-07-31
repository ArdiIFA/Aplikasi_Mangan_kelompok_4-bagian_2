package com.example.kelompok_empat_tubes

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompok_empat_tubes.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var adapter: FoodAdapter
    private lateinit var database: DatabaseReference

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ArrayList and Adapter
        adapter = FoodAdapter(ArrayList())

        // Initialize RecyclerView
        val layoutManager = GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        // Get Firebase database reference
        database = FirebaseDatabase.getInstance().getReference("Makanan")
        // Get data from Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val foodList = ArrayList<Food>()
                // Loop melalui setiap item data dan tambahkan ke foodList
                for (snapshot in dataSnapshot.children) {
                    val Makanan = snapshot.getValue(Food::class.java)
                    Makanan?.let {
                        foodList.add(it)
                        Log.d("Makanan : ","${foodList[0].nama}")
                    }
                }
                // Memperbarui RecyclerView setelah mendapatkan data baru
                adapter.setData(foodList)
            }
            override fun onCancelled(error: DatabaseError) {
                // Penanganan kesalahan saat mengakses Firebase
            }
        })
    }
}