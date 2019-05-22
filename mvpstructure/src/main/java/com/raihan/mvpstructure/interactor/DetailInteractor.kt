package com.raihan.mvpstructure.interactor

import com.raihan.mvpstructure.contract.DetailContract
import com.raihan.mvpstructure.contract.ListContract

open class DetailInteractor: DetailContract.Interactor {

    override fun requestDataFromDataBase(onFinishedListener: DetailContract.Interactor.OnFinishedListener,id : Int) {

    }

    override fun unsubscribe() {
    }

}