package com.example.eggi.network

data class ResponseMovie(
    val page: Int,
    val results: ArrayList<Movie>
)