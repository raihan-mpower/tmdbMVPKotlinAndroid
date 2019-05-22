MvpKotlinAndroid



Things to Improve

  - Use of Dagger/ dependency injection
  - RxJava would better suit MVVM rather than MVP /n MVP separates the presentation layer from the logic so that everything about how the interface works is separated from how it is represented on the screen. The three parts are model, view, and presenter. The model defines the data to be displayed. The view is a passive interface that displays the data and routes commands to the presenter. The presenter acts upon the model and the view. It is responsible for retrieving data from the business logic and formatting it for display in the view.
  /n In MVVM, we invert the dependency arrow between the `View` and `Presenter`, and rename the `Presenter` to `ViewModel`. This newly renamed `ViewModel` does not “act” upon a `View` as a `Presenter` would, but instead provides the `View` with an interface it can bind to in order to observe changes and propagate user actions and updates to the rest of the system.