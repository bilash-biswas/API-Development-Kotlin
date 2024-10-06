package com.example.apidevelopment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apidevelopment.databinding.ActivityMainBinding
import com.example.apidevelopment.student.AddStudentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostAdapter
    private lateinit var postArrayList: ArrayList<Post>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViw.layoutManager = LinearLayoutManager(this)
        binding.recyclerViw.setHasFixedSize(true)

        postArrayList = ArrayList()

        fetchPosts()

        binding.add.setOnClickListener{
            startActivity(Intent(applicationContext, AddStudentActivity::class.java))
        }


    }

    private fun fetchPosts(){
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val response = RetrofitClient.apiService.getPost()
                withContext(Dispatchers.Main){
                    postArrayList = ArrayList(response)
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerViw.visibility = View.VISIBLE

                    adapter = PostAdapter(applicationContext, postArrayList)

                    binding.recyclerViw.adapter = adapter
                }
            }catch (e : Exception){
                Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}