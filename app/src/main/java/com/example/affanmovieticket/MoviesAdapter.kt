package com.example.affanmovieticket

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView


class MoviesAdapter(
    private val context: Context,
    private var moviesList: ArrayList<Movie>
) : RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    open class ItemViewHolder(itemView:View, private val mListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val movieName: MaterialTextView = itemView.findViewById(R.id.movie_name)
        val movieImage:AppCompatImageView = itemView.findViewById(R.id.movie_image)
        val movieStarring:MaterialTextView = itemView.findViewById(R.id.movie_starring)
        val movieCertification:MaterialTextView = itemView.findViewById(R.id.movie_certifications)
        val movieRunningTime:MaterialTextView = itemView.findViewById(R.id.movie_running_time)
        val seatImage:AppCompatImageView = itemView.findViewById(R.id.chair)
        val seatTextView:MaterialTextView = itemView.findViewById(R.id.seats_text_view)
        val fillingFast:AppCompatImageView = itemView.findViewById(R.id.filling_fast)

        fun dataBinding(item: Movie, position: Int,context: Context) {
            Glide
                .with(context)
                .load(item.image)
                .into(movieImage)
            movieName.text = item.name
            movieStarring.text = item.starring
            movieRunningTime.text = item.running_time_mins
            movieCertification.text = item.certification

            itemView.setOnClickListener {
                mListener.onItemClick(layoutPosition)
            }

            if (item.seats_selected == 0){
                seatTextView.text = "${item.seats_remaining} seats remaining"
                seatImage.setColorFilter(Color.parseColor("#ffffff"), android.graphics.PorterDuff.Mode.MULTIPLY)
                seatTextView.setTextColor(Color.parseColor("#ffffff"))
            }
            else{
                seatTextView.text = "${item.seats_selected} seats selected"
                seatImage.setColorFilter(Color.parseColor("#008000"), android.graphics.PorterDuff.Mode.MULTIPLY)
                seatTextView.setTextColor(Color.parseColor("#008000"))

            }

            if (item.seats_remaining != 0 && item.seats_remaining < 3){
                fillingFast.visibility = View.VISIBLE
            }
            else{
                fillingFast.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_row, parent, false)

        return ItemViewHolder(view, mListener!!)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = moviesList[position]
         holder.dataBinding(movie,position,context)

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

}