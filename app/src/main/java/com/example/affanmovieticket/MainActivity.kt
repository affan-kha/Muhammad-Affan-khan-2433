package com.example.affanmovieticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), MoviesAdapter.OnItemClickListener {

    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: MoviesAdapter

    companion object{
        var moviesList = mutableListOf<Movie>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.movies_recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MoviesAdapter(this,moviesList as ArrayList<Movie>)
        recyclerview.adapter = adapter
        adapter.setOnItemClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        if (moviesList.isEmpty()){
            moviesList.addAll(Movie.getMovieData())
            adapter.notifyItemRangeChanged(0,moviesList.size)
        }
        else{
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(position: Int) {
        val item = moviesList[position]

        startActivity(Intent(this,MovieActivity::class.java).apply {
            putExtra("MOVIE",item)
            putExtra("INDEX",position)
        })
    }
}