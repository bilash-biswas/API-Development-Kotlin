package com.example.apidevelopment.student

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apidevelopment.databinding.ActivityAddStudentBinding
import retrofit2.Call
import retrofit2.Callback

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addStudentBtn.setOnClickListener {
            addStudent()
        }


    }

    fun addStudent() {
        val student = Student(
            name = binding.studentName.text.toString(),
            email = binding.studentEmail.text.toString(),
            salary = binding.studentSalary.text.toString(),
            id = null,
            time = null
        )
        try {
            StudentRetrofitClient.apiService.insertStudent(student)
                .enqueue(object : Callback<Response> {
                    override fun onResponse(
                        call: retrofit2.Call<Response>,
                        response: retrofit2.Response<Response>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null && responseBody.status == "success") {
                                Toast.makeText(
                                    this@AddStudentActivity,
                                    "Success" + responseBody.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    this@AddStudentActivity,
                                    "Failed to add student",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@AddStudentActivity,
                                "Server error: ${response.code()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<Response>, t: Throwable) {
                        Toast.makeText(
                            this@AddStudentActivity,
                            "Error: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("response", t.message.toString())
                    }
                })
        }catch (e : Exception){
            Log.e("error", e.message.toString())
        }
    }
}