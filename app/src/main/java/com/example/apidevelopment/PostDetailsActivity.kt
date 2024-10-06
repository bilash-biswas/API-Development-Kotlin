package com.example.apidevelopment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apidevelopment.databinding.ActivityPostDetailsBinding

class PostDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.id.text = intent.getIntExtra("id", 0).toString()
        binding.title.text = intent.getStringExtra("title")
        binding.body.text = intent.getStringExtra("body")
    }
}