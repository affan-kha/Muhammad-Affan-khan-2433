package com.example.affanmovieticket

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import kotlin.math.abs


class MovieActivity : AppCompatActivity() {

    private var movie:Movie?=null
    private lateinit var movieName: MaterialTextView
    private lateinit var movieDescription: MaterialTextView
    private lateinit var movieImage: AppCompatImageView
    private lateinit var movieStarring: MaterialTextView
    private lateinit var movieCertification: MaterialTextView
    private lateinit var movieRunningTime: MaterialTextView
    private lateinit var seatImage: AppCompatImageView
    private lateinit var seatTextView: MaterialTextView
    private lateinit var decrementImage:AppCompatImageView
    private lateinit var incrementImage:AppCompatImageView
    private lateinit var seatsCounter:MaterialTextView
    private var counter = 0
    private var index = 0
    private var tempRemainingSeats = 0
    private var finalRemainingSeats = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        movieName = findViewById(R.id.movie_name)
        movieDescription = findViewById(R.id.movie_description)
        movieImage = findViewById(R.id.movie_image)
        movieStarring = findViewById(R.id.movie_starring)
        movieCertification = findViewById(R.id.movie_certifications)
        movieRunningTime = findViewById(R.id.movie_running_time)
        seatImage = findViewById(R.id.chair)
        seatTextView = findViewById(R.id.seats_text_view)
        decrementImage = findViewById(R.id.seat_decrement_view)
        incrementImage = findViewById(R.id.seat_increment_view)
        seatsCounter = findViewById(R.id.seat_counter_view)


        if (intent != null && intent.hasExtra("MOVIE")){
            index = intent.getIntExtra("INDEX",0)
            movie = intent.getSerializableExtra("MOVIE") as Movie

            Glide
                .with(this)
                .load(movie!!.image)
                .into(movieImage)
            movieName.text = movie!!.name
            movieDescription.text = movie!!.description
            movieStarring.text = movie!!.starring
            movieRunningTime.text = movie!!.running_time_mins
            movieCertification.text = movie!!.certification
            tempRemainingSeats = movie!!.seats_remaining + movie!!.seats_selected
            if (movie!!.seats_selected == 0){
                seatTextView.text = "${movie!!.seats_remaining} seats remaining"
            }
            else{
                seatTextView.text = "${movie!!.seats_remaining} seats remaining"
                seatsCounter.text = "${movie!!.seats_selected}"
                counter = movie!!.seats_selected

            }

            if (movie!!.seats_selected == 0){
                decrementImage.isEnabled = false
                decrementImage.setColorFilter(Color.parseColor("#cccccc"), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
            else{
                decrementImage.isEnabled = true
                decrementImage.setColorFilter(Color.parseColor("#ffffff"), android.graphics.PorterDuff.Mode.MULTIPLY)
            }

            if (movie!!.seats_remaining == 0){
                incrementImage.isEnabled = false
                incrementImage.setColorFilter(Color.parseColor("#cccccc"), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
            else{
                incrementImage.isEnabled = true
                incrementImage.setColorFilter(Color.parseColor("#ffffff"), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
        }

        decrementImage.setOnClickListener {
            counter -=1
            if (counter >=0) {
                seatsCounter.text = "$counter"
                if (counter == 0) {
                    decrementImage.isEnabled = false
                    decrementImage.setColorFilter(
                        Color.parseColor("#cccccc"),
                        android.graphics.PorterDuff.Mode.MULTIPLY
                    )
                } else {
                    decrementImage.isEnabled = true
                    decrementImage.setColorFilter(
                        Color.parseColor("#ffffff"),
                        android.graphics.PorterDuff.Mode.MULTIPLY
                    )
                    incrementImage.isEnabled = true
                    incrementImage.setColorFilter(
                        Color.parseColor("#ffffff"),
                        android.graphics.PorterDuff.Mode.MULTIPLY
                    )
                }
                val result = abs(counter - tempRemainingSeats)
                finalRemainingSeats = result
                seatTextView.text = "$result seats remaining"
            }
        }

        incrementImage.setOnClickListener {
            counter +=1
            if (counter <= tempRemainingSeats) {
                seatsCounter.text = "$counter"

                if (counter == tempRemainingSeats) {
                    incrementImage.isEnabled = false
                    incrementImage.setColorFilter(
                        Color.parseColor("#cccccc"),
                        android.graphics.PorterDuff.Mode.MULTIPLY
                    )
                } else {
                    incrementImage.isEnabled = true
                    incrementImage.setColorFilter(
                        Color.parseColor("#ffffff"),
                        android.graphics.PorterDuff.Mode.MULTIPLY
                    )
                    decrementImage.isEnabled = true
                    decrementImage.setColorFilter(
                        Color.parseColor("#ffffff"),
                        android.graphics.PorterDuff.Mode.MULTIPLY
                    )
                }
                val result = tempRemainingSeats - counter
                finalRemainingSeats = result
                seatTextView.text = "$result seats remaining"
            }
        }

    }


    override fun onBackPressed() {
        movie!!.seats_remaining = finalRemainingSeats
        movie!!.seats_selected = seatsCounter.text.toString().toInt()
        MainActivity.moviesList.removeAt(index)
        MainActivity.moviesList.add(index,movie!!)
        super.onBackPressed()
    }
}