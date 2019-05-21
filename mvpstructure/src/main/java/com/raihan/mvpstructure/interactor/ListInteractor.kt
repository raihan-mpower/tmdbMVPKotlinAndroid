package com.raihan.mvpstructure.interactor

import com.raihan.mvpstructure.contract.ListContract

open class ListInteractor:ListContract.Interactor{

    override fun requestDataFromServer(onFinishedListener: ListContract.Interactor.OnFinishedListener) {
    }

    override fun requestDataFromDataBase(onFinishedListener: ListContract.Interactor.OnFinishedListener) {
    }

    override fun unsubscribe() {
    }

}