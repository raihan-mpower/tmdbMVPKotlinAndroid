package com.raihan.mvpKotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.raihan.mvpKotlin.interactor.MovieListInteractor
import com.raihan.mvpKotlin.model.Movies
import com.raihan.mvpKotlin.presenter.MovieListPresenter
import com.raihan.mvpstructure.contract.ListContract

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
        }
    }



}
