package com.davidquint.randomdog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davidquint.randomdog.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import coil.load


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dogApi: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dogApi = retrofit.create(ApiInterface::class.java)

        fetchRandomDogImage()

        binding.changeDog.setOnClickListener{
            fetchRandomDogImage()
        }

    }

    private fun fetchRandomDogImage() {
        GlobalScope.launch {
            try {
                val response = dogApi.getRandomDogImage()
                val imageUrl = response.message
                binding.imageView.load(imageUrl)
                binding.status.text = response.status

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

