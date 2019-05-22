package com.raihan.mvpKotlin.view.fragment

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import com.raihan.mvpKotlin.adapter.MovieAdapter
import com.raihan.mvpKotlin.R
import com.raihan.mvpKotlin.interactor.MovieListInteractor
import com.raihan.mvpKotlin.model.Movies
import com.raihan.mvpKotlin.presenter.MovieListPresenter
import com.raihan.mvpKotlin.view.activity.DetailActivity
import com.raihan.mvpstructure.contract.ListContract
import kotlinx.android.synthetic.main.fragment_main.*


class MainActivityFragment : Fragment(),ListContract.View {

    lateinit var presenter :MovieListPresenter
    lateinit var interactor :MovieListInteractor
    var emptyAdapterArrayList : List<Movies> = ArrayList<Movies>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        interactor = MovieListInteractor(activity as Context);
        presenter = MovieListPresenter(interactor,this)

        recycler_view_movie_list.layoutManager = LinearLayoutManager(activity as Context)
        recycler_view_movie_list.layoutManager = GridLayoutManager(activity as Context, 2)
        recycler_view_movie_list.adapter =
                MovieAdapter(emptyAdapterArrayList, activity as Context, presenter)

        presenter.attachView(this);
        presenter = MovieListPresenter(interactor,this)
        presenter.callInteractorToFetchDataFromServer()
        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

    }

    override fun <T> setDataToRecyclerView(adapterArrayList: List<T>) {
        recycler_view_movie_list.adapter =
                MovieAdapter(
                    adapterArrayList as List<Movies>,
                    activity as Context,
                    presenter
                )
        (recycler_view_movie_list.adapter as MovieAdapter).notifyDataSetChanged()
    }

    override fun launchDetail(id: Int) {
        val intent = Intent(activity as Context, DetailActivity::class.java)
        val message = id

        intent.putExtra("movieId", message.toString())
        startActivity(intent)
    }
}

