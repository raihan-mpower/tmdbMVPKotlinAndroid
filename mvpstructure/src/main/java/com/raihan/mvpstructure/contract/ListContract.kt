package com.raihan.mvpstructure.contract


class ListContract{

    interface Presenter{
        fun attachView(view: View)
        fun callInteractorToFetchDataFromServer();
        fun callInteractorToFetchDataFromDataBase();
        fun launchDetailActivity(id: Int)
        fun unsubscribe()
    }

    interface View{
        fun <T> setDataToRecyclerView(adapterArrayList: List<T>)
        fun launchDetail(id: Int)
    }

    interface Interactor{
        interface OnFinishedListener {
            fun <T> onFinished(list: List<T>)
            fun onFailure(t: Throwable)
            fun serverCallDone()
        }
        fun requestDataFromServer(onFinishedListener: OnFinishedListener)
        fun requestDataFromDataBase(onFinishedListener: OnFinishedListener)
        fun unsubscribe()
    }


}