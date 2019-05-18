package com.raihan.mvpKotlin.interactor

import com.raihan.mvpstructure.contract.ListContract
import com.raihan.mvpstructure.interactor.ListInteractor

class MovieListInteractor: ListInteractor() {

    override fun requestDataFromServer(onFinishedListener: ListContract.Interactor.OnFinishedListener) {
        super.requestDataFromServer(onFinishedListener)

    }

}