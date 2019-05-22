package com.raihan.mvpKotlin.presenter

import com.raihan.mvpstructure.contract.DetailContract
import com.raihan.mvpstructure.contract.ListContract
import com.raihan.mvpstructure.presenter.DetailPresenter
import com.raihan.mvpstructure.presenter.ListPresenter

class MovieDetailPresenter(interactor: DetailContract.Interactor, view: DetailContract.View) : DetailPresenter(interactor,view) {


    override fun onFinished(movies: Any) {
        view.populateDetails(movies)
    }

    override fun onFailure(t: Throwable) {
    }


}