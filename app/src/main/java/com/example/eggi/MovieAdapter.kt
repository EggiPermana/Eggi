package com.example.eggi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.eggi.databinding.ItemMovieBinding
import com.example.eggi.network.Movie

// Adapter untuk membuat list dengan ada 2 parameter yang pertama untuk data, dan kedua untuk action
class MovieAdapter(
    private val listMovie: List<Movie>,
    private val itemClickHandler: ItemClickHandler
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // Mengembalikan view yang digunakan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    // Disini hanya memberikan data pada fungsi bind yang terdapat pada MovieViewHolder
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    // Kita mengembalikan jumlah data dari listMovie
    override fun getItemCount(): Int = listMovie.size


    // Class viewholder untuk bagian deklarasi views atau set views
    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvMovieName.text = movie.title
            // Memberikan perintah berbeda pada onSpinnerClicked sesuai dengan index pada item
            binding.spinnerMovie.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    when (p2) {
                        0 -> {

                        }
                        1 -> {
                            itemClickHandler.onSpinnerClicked(movie.title, "hapus")
                        }
                        2 -> {
                            itemClickHandler.onSpinnerClicked(movie.title, "lupakan")
                        }
                        else -> {

                        }
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }

}