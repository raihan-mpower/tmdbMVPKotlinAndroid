package com.raihan.mvpKotlin.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import androidx.test.core.app.ApplicationProvider;
import com.raihan.mvpKotlin.model.Movies;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class MoviesDaoTest {
    private MoviesDao moviesDao;
    private MovieLocalDb db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, MovieLocalDb.class).allowMainThreadQueries().build();
        moviesDao = db.movieListDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeMovieAndReadById() throws Exception {
        Movies movie = new Movies("1","10","100","false","video","movie1","","","","movie1","","","","");
        ArrayList<Movies> toInsert = new ArrayList<Movies>();
        toInsert.add(movie);
        moviesDao.insertAll(toInsert);
        String titleofInsertedMovie = moviesDao.findMovieWithId("1").map(Movies::getTitle).blockingGet();
        assertThat(movie.getTitle(), equalTo(titleofInsertedMovie));
    }

    @Test
    public void writeMovieAndReadInList() throws Exception {
        Movies movie = new Movies("1","10","100","false","video","movie1","","","","movie1","","","","");
        ArrayList<Movies> toInsert = new ArrayList<Movies>();
        toInsert.add(movie);
        moviesDao.insertAll(toInsert);
        List<Movies> allmovies= moviesDao.getAll().blockingGet();
        assertThat(toInsert.get(0).getId(), equalTo(allmovies.get(0).getId()));
    }

}