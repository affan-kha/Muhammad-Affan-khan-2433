package com.example.affanmovieticket

import kotlin.random.Random

data class Movie(
    val certification: String,
    val description: String,
    val image: String,
    val name: String,
    val running_time_mins: String,
    var seats_remaining: Int,
    var seats_selected: Int,
    val starring: String
) : java.io.Serializable {

    companion object {
        fun getMovieData(): List<Movie> {
            val movieList = mutableListOf<Movie>()

            movieList.add(
                Movie(
                    "TBC",
                    "Peter Quill, still reeling from the loss of Gamora, must rally his team around him to defend the universe along with protecting one of their own.",
                    "https://www.myvue.com/-/media/images/film%20and%20events/march%202023/films/gotg3-herodv3.jpg?sc=.99",
                    "GUARDIANS OF THE GALAXY VOL. 3",
                    "2hrs 23mins",
                    getRandomNumber(),
                    0,
                    "Dave Bautista, Chris Pratt, Zoe Saldana, Vin Diesel, Karen Gillan, Bradley Cooper",
                )
            )
            movieList.add(
                Movie(
                    "TBC",
                    "A charming thief and a band of unlikely adventurers undertake an epic heist to retrieve a lost relic, but things go dangerously awry when they run afoul of the wrong people.",
                    "https://www.myvue.com/-/media/images/film%20and%20events/march%202023/carousels/07932%20dnd%20vue%20carousel%20%201600x540px%20aw2.jpg?sc=.99",
                    "DUNGEONS & DRAGONS: HONOUR AMONG THIEVES",
                    "2hrs 14mins",
                    getRandomNumber(),
                    0,
                    "Chris Pine, Michelle Rodriguez, Justice Smith, Regé-Jean Page, Sophia Lillis, Chloe Coleman, Daisy Head, Hugh Grant",
                )
            )
            movieList.add(
                Movie(
                    "16",
                    "John Wick (Keanu Reeves) uncovers a path to defeating The High Table. But before he can earn his freedom, Wick must face off against a new enemy with powerful alliances across the globe and forces that turn old friends into foes.",
                    "https://m.media-amazon.com/images/M/MV5BZDdkZTg0ZjYtNjIzNS00YzM3LWI4NzUtNmJiMWMxYmUwODg1XkEyXkFqcGdeQWFybm8@._V1_.jpg",
                    "JOHN WICK: CHAPTER 4",
                    "2hrs 49mins",
                    getRandomNumber(),
                    0,
                    "Keanu Reeves, Donnie Yen, Bill Skarsgård",
                )
            )
            movieList.add(
                Movie(
                    "PG",
                    "Vue Members Exclusive: Book tickets by 4 April and receive a complimentary power-up on your next visit to see Mario! See myvue.com/legal for terms. - A plumber named Mario travels through an underground labyrinth with his brother, Luigi, trying to save a captured princess.",
                    "https://www.myvue.com/-/media/images/film%20and%20events/march%202023/mario/mario-article-herod.jpg?sc=.99",
                    "THE SUPER MARIO BROS. MOVIE",
                    "1hr 32mins",
                    getRandomNumber(),
                    0,
                    "Chris Pratt, Anya Taylor-Joy, Charlie Day, Jack Black, Seth Rogen",
                )
            )
            return movieList
        }
        private fun getRandomNumber():Int{
            return Random.nextInt(0, 15)
        }
    }


}