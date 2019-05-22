MvpKotlinAndroid



# Things that could be improved

  - Use of Dagger/ dependency injection
  - RxJava would better suit MVVM rather than MVP
MVP separates the presentation layer from the logic so that everything about how the interface works is separated from how it is represented on the screen. The three parts are model, view, and presenter. The model defines the data to be displayed. The view is a passive interface that displays the data and routes commands to the presenter. The presenter acts upon the model and the view. It is responsible for retrieving data from the business logic and formatting it for display in the view.
In MVVM, we invert the dependency arrow between the `View` and `Presenter`, and rename the `Presenter` to `ViewModel`. This newly renamed `ViewModel` does not “act” upon a `View` as a `Presenter` would, but instead provides the `View` with an interface it can bind to in order to observe changes and propagate user actions and updates to the rest of the system.

With MVVM it’s clear where you subscribe to data. You bind or subscribe to asynchronous events in the ViewModel. MVVM terminology supports it. Views bind or subscribe to events off the ViewModel via Subjects.
MVP can get confusing where to subscribe or bind. Presenter? View Controller?
   - My use of Kotlin also has room for improvement as I am fairly new to using it for android development
   - One major improvement that could have been done was to merge both stream/observables from database and api(while saving to db after each successful calls), by using mergedelayerror function. Right now the data is being saved to the database after a call to the api and then the callback to presenter would call the interactor to fetch data from the database.

# Architecture Explanation
  - I have implemented MVP VIPER Architecture, VIPER stands for View, Interactor, Presenter, Entity, and Router. This five-layer organization aims to assign different tasks to each entity, following the Single Responsibility Principle. The basic idea behind VIPER and other Clean Architecture patterns is to create a cleaner and more modular structure to isolate the app’s dependencies and improve the flow of data within the app. As this is a small app with navigation limited between two activities I have omitted the router part.
  What an Ideal MVP VIPER architecture would look like
  ![N|Solid](https://koenig-media.raywenderlich.com/uploads/2018/02/viper-scheme-480x273.png)

  I have separated my Contracts two types for the purpose of application. A list Contract and a detail contract to display list and detail of the movie respectively.
  Below diagram shows what the structure looks like for my list contract. That is responsible for showing the list view in the launch activity.
  ![N|Solid](https://github.com/raihan-mpower/tmdbMVPKotlinAndroid/blob/master/_mvpkotlin.png?raw=true)
  
  As seen in the above diagram, the view would ask the presenter to fetch data which would in turn call the interactor, which does most of the heavy lifting (network and database calls) by storing and retriving data from the model layer which consists of the entity( movies in this case) and the db/ repositories.
