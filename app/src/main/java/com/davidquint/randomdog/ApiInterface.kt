package com.davidquint.randomdog

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/breeds/image/random")
    suspend fun getRandomDogImage(): DogResponse

}