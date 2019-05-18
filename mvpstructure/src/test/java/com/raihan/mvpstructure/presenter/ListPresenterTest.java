package com.raihan.mvpstructure.presenter;

import com.raihan.mvpstructure.contract.ListContract;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ListPresenterTest {

    @Mock
    ListContract.Interactor interactor;

    @Mock
    ListContract.View view;

   ListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new ListPresenter(interactor);
        presenter.attachView(view);
    }

    @Test
    public void createsNotNullObject() {
        assertNotNull(new ListPresenter(interactor));
    }

    @Test
    public void callInteractorToFetchDataFromServer_callsInteractorrequestDataFromServer(){
        presenter.callInteractorToFetchDataFromServer();
        Mockito.verify(interactor,Mockito.times(1)).requestDataFromServer(presenter);
    }

    @Test
    public void callInteractorToFetchDataFromDatabase_callsInteractorrequestDataFromDatabase(){
        presenter.callInteractorToFetchDataFromDataBase();
        Mockito.verify(interactor,Mockito.times(1)).requestDataFromDataBase(presenter);
    }

}
