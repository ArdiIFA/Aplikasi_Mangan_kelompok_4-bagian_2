package com.example.kelompok_empat_tubes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.bumptech.glide.Glide
import com.example.kelompok_empat_tubes.databinding.ActivityDetailProductBinding

class DetailProduct : AppCompatActivity() {
    lateinit var binding : ActivityDetailProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama")
        val gambar = intent.getStringExtra("gambar")
        val harga = intent.getStringExtra("harga")
        val isi = intent.getStringExtra("isi")

        Glide.with(this).load(gambar).into(binding.gambar)
        binding.nama.text = nama
        binding.harga.text = harga
        binding.isi.text = isi

        val nomorbtn: Button = findViewById(R.id.button)

        nomorbtn.setOnClickListener {
            val phoneNumber = 6281999519577
            val message = "Permisi, Saya ingin memesan oleh-oleh!"
            val sendIntent = Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=$message"))
            startActivity(sendIntent)
        }
    }
}