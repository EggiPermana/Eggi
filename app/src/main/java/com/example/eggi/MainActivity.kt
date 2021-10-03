package com.example.eggi

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eggi.databinding.ActivityMainBinding
import com.example.eggi.network.ResponseMovie
import com.example.eggi.network.RetrofitApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), ItemClickHandler {

    // menggunakan bantuan view-binding agar tidak perlu memanggil findviewbyid berulang kali
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // mengeset jenis layout manager, pada kasus ini adalah list
        binding.rvMovie.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //pemanggilan API dengan method enqueue
        RetrofitApp().getMovieService().getTopRatedMovies(API_KEY)
            .enqueue(object : Callback<ResponseMovie> {
                override fun onResponse(
                    call: Call<ResponseMovie>,
                    response: Response<ResponseMovie>
                ) {
                    // mendapat data retrofit dengan memanggil object response
                    val data = response.body()
                    val movies = data?.results
                    //Kita memasukkan data pada parameter yang ada pada Recyclerview
                    binding.rvMovie.adapter = MovieAdapter(data?.results!!, this@MainActivity)

                    //listName hanya sebagai penampung untuk data name dari movie
                    val listName = ArrayList<String>()
                    for (i in 0 until movies?.size!!){
                        listName.add(movies[i].title)
                    }
                    // Set array adapter dengan arrylist yang telah kita buat tadi pada baris 45
                    val arrayAdapter = ArrayAdapter(this@MainActivity, R.layout.simple_list_item_1,
                        listName)
                    binding.autoCompleteMovie.setAdapter(arrayAdapter)
                }

                override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                    Log.d("Main", "${t.printStackTrace()}")
                }
            })
    }

    // Memberikan action pada item dengan pemanfaatan interface
    override fun onSpinnerClicked(movieName: String, actionName: String) {
        Toast.makeText(this, "test $movieName telah di$actionName", Toast.LENGTH_SHORT).show()
    }

}

const val API_KEY = "83bd87b6c77400db6e99add8541fcc63"
