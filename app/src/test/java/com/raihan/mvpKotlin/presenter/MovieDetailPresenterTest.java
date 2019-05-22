package com.raihan.mvpKotlin.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import com.raihan.mvpKotlin.api.ApiServiceInterface;
import com.raihan.mvpKotlin.db.MovieLocalDb;
import com.raihan.mvpKotlin.db.MoviesDao;
import com.raihan.mvpKotlin.interactor.MovieDetailInteractor;
import com.raihan.mvpKotlin.interactor.MovieListInteractor;
import com.raihan.mvpKotlin.model.MovieList;
import com.raihan.mvpKotlin.model.Movies;
import com.raihan.mvpstructure.contract.DetailContract;
import com.raihan.mvpstructure.contract.ListContract;
import com.raihan.mvpstructure.presenter.DetailPresenter;
import com.raihan.mvpstructure.presenter.ListPresenter;
import io.reactivex.Maybe;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

public class MovieDetailPresenterTest {

    @Mock
    MovieDetailInteractor interactor;

    @Mock
    DetailContract.View view;

    @Mock
    Context context;

    @Mock
    ApiServiceInterface mockApi;

    @Mock
    MovieLocalDb mockLocalDb;

    @Mock
    MoviesDao moviesDao;

    @Mock
    Maybe<List<Movies>> mockMovieListObservable;

    @Mock
    DetailContract.Interactor.OnFinishedListener mockOnFinishedListener;

    MovieList movieList;

    @Mock
    List<Movies> mockMovies;

    MovieDetailPresenter presenter;

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
        presenter = new MovieDetailPresenter(interactor,view);
//        Mockito.doReturn(mockApi).when(interactor).getApiInterFace();
//        Mockito.doReturn(mockLocalDb).when(interactor).getLocalDb();
//        Mockito.doReturn(moviesDao).when(mockLocalDb).movieListDao();
//        Mockito.doReturn(mockMovieListObservable).when(moviesDao).findMovieWithId(anyString());
    }

    @Test
    public void testcallInteractorToFetchDataFromDatabaseCallsInteractorDataFetchFromDatabase(){
        presenter.requestDataFromDataBase(1);
        Mockito.verify(interactor,Mockito.times(1)).requestDataFromDataBase(presenter,1);
    }

}
