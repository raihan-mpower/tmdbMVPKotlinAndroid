package com.raihan.mvpKotlin.view.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity;
import com.raihan.mvpKotlin.R
import com.raihan.mvpKotlin.view.fragment.DetailActivityFragment

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

    }

     fun returnMovieID():String{
        return intent.extras.getString("movieId")
    }

}
