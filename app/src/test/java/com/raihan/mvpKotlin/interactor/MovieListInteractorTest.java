package com.raihan.mvpKotlin.interactor;

import android.content.Context;
import android.support.annotation.NonNull;
import com.raihan.mvpKotlin.api.ApiServiceInterface;
import com.raihan.mvpKotlin.db.MovieLocalDb;
import com.raihan.mvpKotlin.db.MoviesDao;
import com.raihan.mvpKotlin.model.MovieList;
import com.raihan.mvpKotlin.model.Movies;
import com.raihan.mvpstructure.contract.ListContract;
import com.raihan.mvpstructure.presenter.ListPresenter;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class MovieListInteractorTest {

    MovieListInteractor interactor;

    @Mock
    ListContract.View view;

    @Mock
    Context context;

    @Mock
    ApiServiceInterface mockApi;

    @Mock
    MovieLocalDb mockLocalDb;

    @Mock
    MoviesDao moviesDao;

    @Mock
    Observable<MovieList> mockMovieListObservable;

    @Mock
    ListContract.Interactor.OnFinishedListener mockOnFinishedListener;

    MovieList movieList;

    @Mock
    List<Movies> mockMovies;

    ListPresenter presenter;

    @BeforeClass
    public static void setUpRxSchedulers() {
        final Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> scheduler) throws Exception {
                return immediate;
            }
        });
        RxJavaPlugins.setInitComputationSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> scheduler) throws Exception {
                return immediate;
            }
        });
        RxJavaPlugins.setInitNewThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> scheduler) throws Exception {
                return immediate;
            }
        });
        RxJavaPlugins.setInitSingleSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> scheduler) throws Exception {
                return immediate;
            }
        });
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> scheduler) throws Exception {
                return immediate;
            }
        });
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        interactor = spy(new MovieListInteractor(context));
        Mockito.doReturn(mockApi).when(interactor).getApiInterFace();
        Mockito.doReturn(mockLocalDb).when(interactor).getLocalDb();
        Mockito.doReturn(moviesDao).when(mockLocalDb).movieListDao();


        movieList = new MovieList(new ArrayList<Movies>());

    }

    @Test
    public void testInteractorPullDataMethodCallsOnFinishInSuccess(){
        Mockito.when(mockApi.getList()).thenReturn(Observable.just(movieList));
        interactor.requestDataFromServer(mockOnFinishedListener);
        Mockito.verify(mockOnFinishedListener,Mockito.times(1)).serverCallDone();
    }

    @Test
    public void testInteractorPullDataMethodCallsOnFailureInFailure(){
        Exception expectedException = new Exception();
        Mockito.when(mockApi.getList()).thenReturn(Observable.error(expectedException));
        interactor.requestDataFromServer(mockOnFinishedListener);
        Mockito.verify(mockOnFinishedListener,Mockito.times(1)).onFailure(expectedException);
    }
}
