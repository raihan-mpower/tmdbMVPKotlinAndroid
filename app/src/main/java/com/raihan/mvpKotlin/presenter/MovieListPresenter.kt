package com.raihan.mvpKotlin.presenter

import com.raihan.mvpstructure.contract.ListContract
import com.raihan.mvpstructure.presenter.ListPresenter

class MovieListPresenter(interactor: ListContract.Interactor,view: ListContract.View) : ListPresenter(interactor,view) {

    override fun attachView(view: ListContract.View) {
        this.view = view;
    }

    override fun onFailure(t: Throwable) {

    }

    override fun callInteractorToFetchDataFromServer() {
        interactor.requestDataFromServer(this);
    }

    override fun callInteractorToFetchDataFromDataBase() {
        interactor.requestDataFromDataBase(this);
    }

}