package com.raihan.mvpKotlin.interactor

import android.content.Context
import android.util.Log
import com.raihan.mvpKotlin.api.ApiServiceInterface
import com.raihan.mvpKotlin.db.MovieLocalDb
import com.raihan.mvpKotlin.db.MoviesDao
import com.raihan.mvpKotlin.model.MovieList
import com.raihan.mvpKotlin.model.Movies
import com.raihan.mvpstructure.contract.ListContract
import com.raihan.mvpstructure.interactor.ListInteractor
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription
import java.util.concurrent.Callable


class MovieListInteractor(context: Context) : ListInteractor() {

//    private val api: ApiServiceInterface = ApiServiceInterface.create()

    private val context : Context = context

    private val subscriptions = CompositeDisposable()

    private lateinit var api: ApiServiceInterface

    private lateinit var localdb: MovieLocalDb


    override fun requestDataFromServer(onFinishedListener: ListContract.Interactor.OnFinishedListener) {

        var results = getApiInterFace().getList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io());
        results.subscribe(object: Observer <MovieList>{

            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                subscriptions.addAll(d);
            }

            override fun onNext(t: MovieList) {
                Completable.fromAction {
                    getLocalDb().movieListDao()
                        .insertAll(t.results)
                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object: CompletableObserver  {
                    override fun onSubscribe(d: Disposable) {
                        subscriptions.addAll(d)
                    }

                    override fun onError(e: Throwable) {
                        onFinishedListener.onFailure(e)
                    }

                    override fun onComplete() {
                        onFinishedListener.serverCallDone()
                    }
                })

            }


            override fun onError(e: Throwable) {
                onFinishedListener.onFailure(e);
            }

        })
    }

    override fun requestDataFromDataBase(onFinishedListener: ListContract.Interactor.OnFinishedListener) {
        var results = getUsersFromDb()
//        var results = Observable.just(getLocalDb().movieListDao().all)
//        var results = Observable.fromCallable<List<Movies>> { getLocalDb().movieListDao().all };
        results.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io());
        results.subscribe(object: Observer <List<Movies>>{
            override fun onSubscribe(d: Disposable) {
                subscriptions.addAll(d);
            }

            override fun onComplete() {
            }

            override fun onNext(t: List<Movies>) {
                onFinishedListener.onFinished(t)
            }


            override fun onError(e: Throwable) {
                onFinishedListener.onFailure(e);
            }

        })
    }

    override fun unsubscribe() {
        subscriptions.clear();
    }

    fun getApiInterFace(): ApiServiceInterface {
        api = ApiServiceInterface.create();
        return api;
    }

    fun getLocalDb(): MovieLocalDb{
        localdb = MovieLocalDb.create(context)
        return localdb
    }

    fun getUsersFromDb(): Observable<List<Movies>> {
        return getLocalDb().movieListDao().all.filter { it.isNotEmpty() }
            .toObservable()
    }

}