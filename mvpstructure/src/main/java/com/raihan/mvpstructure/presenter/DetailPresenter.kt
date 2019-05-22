package com.raihan.mvpstructure.presenter

import com.raihan.mvpstructure.contract.DetailContract

open class DetailPresenter:DetailContract.Presenter,DetailContract.Interactor.OnFinishedListener {

    var interactor:DetailContract.Interactor

    var view:DetailContract.View

    constructor(interactor: DetailContract.Interactor,view: DetailContract.View){
        this.interactor = interactor;
        this.view = view;
    }


    override fun requestDataFromDataBase(id: Int) {
        interactor.requestDataFromDataBase(this,id)
    }

    override fun unsubscribe() {
        interactor.unsubscribe()
    }


    override fun onFinished(movies: Any) {

    }

    override fun onFailure(t: Throwable) {
    }

}
