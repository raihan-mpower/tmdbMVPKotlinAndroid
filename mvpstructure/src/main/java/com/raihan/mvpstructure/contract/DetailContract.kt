package com.raihan.mvpstructure.contract

class DetailContract{

    interface Presenter{
        fun requestDataFromDataBase(id: Int)
        fun unsubscribe()
    }
    interface Interactor{
        interface OnFinishedListener {
            fun onFinished(details: Any)
            fun onFailure(t: Throwable)
        }
        fun requestDataFromDataBase(onFinishedListener: OnFinishedListener, id :Int)
        fun unsubscribe()
    }
    interface View{
        fun populateDetails(movies: Any)
    }

}