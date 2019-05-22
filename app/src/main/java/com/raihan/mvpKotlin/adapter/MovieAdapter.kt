package com.raihan.mvpKotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.raihan.mvpKotlin.R
import com.raihan.mvpKotlin.model.Movies
import com.raihan.mvpKotlin.presenter.MovieListPresenter
import com.raihan.mvpKotlin.util.Constants

class MovieAdapter(
    val items: List<Movies>,
    val context: Context,
    presenter: MovieListPresenter
) : RecyclerView.Adapter<ViewHolder>() {

    var listPresenter = presenter

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.movie_view_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.title?.text = items.get(position).title
        holder?.description?.text = items.get(position).overview

        holder?.release_year?.text = items.get(position).release_date
        Glide.with(context)
            .load(Constants.imageBaseURL+items.get(position).poster_path)
            .into(holder?.poster)
        holder?.itemView.setOnClickListener(View.OnClickListener {
            listPresenter.launchDetailActivity(items.get(position).id.toInt())
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    @BindView(R.id.txt_notice_title)
    lateinit var title: TextView

    @BindView(R.id.moviethumbnail)
    lateinit var poster: ImageView

    @BindView(R.id.txt_notice_brief)
    lateinit var description: TextView

    @BindView(R.id.txt_release_year)
    lateinit var release_year: TextView

    init {
        ButterKnife.bind(this, view)
    }
}