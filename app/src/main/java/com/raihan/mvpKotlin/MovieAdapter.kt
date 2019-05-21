package com.raihan.mvpKotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.raihan.mvpKotlin.model.Movies
import com.raihan.mvpKotlin.util.Constants
import kotlinx.android.synthetic.main.movie_view_row.view.*

class MovieAdapter(val items : List<Movies>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_view_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvAnimalType?.text = items.get(position).title
        Glide.with(context)
            .load(Constants.imageBaseURL+items.get(position).poster_path)
            .into(holder?.poster)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvAnimalType = view.txt_notice_title
    val poster = view.moviethumbnail
}