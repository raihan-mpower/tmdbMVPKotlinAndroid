package com.raihan.mvpstructure.presenter

import com.raihan.mvpstructure.contract.ListContract

open class ListPresenter:ListContract.Presenter,ListContract.Interactor.OnFinishedListener{


    lateinit var interactor:ListContract.Interactor

    lateinit var view:ListContract.View

    constructor(interactor: ListContract.Interactor,view: ListContract.View){
        this.interactor = interactor;
        this.view = view;
    }

    override fun attachView(view: ListContract.View) {
        this.view = view;
    }

    override fun <T> onFinished(list: List<T>) {
        view.setDataToRecyclerView(list);
    }

    override fun onFailure(t: Throwable) {

    }
    override fun serverCallDone() {
        callInteractorToFetchDataFromDataBase()
    }

    override fun callInteractorToFetchDataFromServer() {
        interactor.requestDataFromServer(this);
    }

    override fun callInteractorToFetchDataFromDataBase() {
        interactor.requestDataFromDataBase(this);
    }

}
