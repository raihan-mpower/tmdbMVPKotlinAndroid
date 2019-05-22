package com.raihan.mvpKotlin.view.fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.raihan.mvpKotlin.R
import com.raihan.mvpKotlin.interactor.MovieDetailInteractor
import com.raihan.mvpKotlin.interactor.MovieListInteractor
import com.raihan.mvpKotlin.model.Movies
import com.raihan.mvpKotlin.presenter.MovieDetailPresenter
import com.raihan.mvpKotlin.presenter.MovieListPresenter
import com.raihan.mvpKotlin.util.Constants
import com.raihan.mvpKotlin.view.activity.DetailActivity
import com.raihan.mvpstructure.contract.DetailContract
import com.raihan.mvpstructure.interactor.DetailInteractor
import java.util.*

/**
 * A placeholder fragment containing a simple view.
 */
class DetailActivityFragment : Fragment(),DetailContract.View {

    @BindView(R.id.detailposter)
    lateinit var poster: ImageView
    @BindView(R.id.movie_title)
    lateinit var title: TextView
    @BindView(R.id.movie_release_year)
    lateinit var release_year: TextView
    @BindView(R.id.movie_rating)
    lateinit var movie_rating: TextView
    @BindView(R.id.movie_votes)
    lateinit var movie_votes: TextView
    @BindView(R.id.movie_language)
    lateinit var movie_language: TextView
    @BindView(R.id.movie_description)
    lateinit var movie_description: TextView

    lateinit var presenter : MovieDetailPresenter
    lateinit var interactor : MovieDetailInteractor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        interactor = MovieDetailInteractor(activity as Context)
        presenter = MovieDetailPresenter(interactor,this)
        presenter.requestDataFromDataBase((activity as DetailActivity).returnMovieID().toInt())
    }

    fun fragmentDraw(id: String){

    }

    override fun populateDetails(movies: Any) {
        var movie = movies as Movies
        Glide.with(context)
            .load(Constants.imageBaseURL+movie.poster_path)
            .into(poster)
        title.text = movie.title
        release_year.text = "Release Date: "+movie.release_date;
        movie_rating.text = "Rating: "+movie.vote_average;
        movie_votes.text= "Total Votes: "+movie.vote_count
        movie_description.text = movie.overview
        movie_language.text = "Language: "+movie.original_language
    }
}
