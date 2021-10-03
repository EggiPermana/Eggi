package com.example.eggi.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

    interface MovieService {

        // Pemangggilan end-point berupa top-rated movie dengan query api_key dari TMDB
        @GET("movie/top_rated")
        fun getTopRatedMovies(
            @Query("api_key") apiKey: String
        ): Call<ResponseMovie>


    }
