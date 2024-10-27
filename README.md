# TicTacToe - Android Game App


<p align="center"> 
    <img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp" alt="App icon" width="100"/>
    <br />
    <b>TicTacToe game developed in Android with Kotlin programming language and Clash Royale UI style</b>
    <br />
    <br />
    <img src="https://github.com/CarlosCGA/TicTacToe/actions/workflows/android.yml/badge.svg" alt="CI badge sign" />
</p>

## About

TicTacToe is fully developed by Carlos Cañete in Android Studio using the Android SDK. It is a game to learn and try something interesting things about firebase and jetpack compose UI customization.


## Architecture - MVVM (Model-View-ViewModel)

This architecture facilitates the separation of concerns, enhancing code maintainability and scalability by dividing it into three main components:

- Model: It manages the application's data and business logic.
- View: It represents the user interface and displays the data from the ViewModel.
- ViewModel: It acts as an intermediary between the Model and the View, managing presentation logic and updating the UI.


## Layout - Jetpack Compose

Modern UI toolkit for building native Android apps using a declarative approach. It simplifies UI development by allowing developers to define how the UI should look directly in code, rather than using XML. It's used to create responsive and dynamic user interfaces more efficiently.


## Dependencies

- `Firebase`: Provides tools for backend services like authentication, database (Firestore, Realtime Database), cloud storage, and push notifications. It enables rapid app development with scalable, serverless solutions and real-time data sync. With built-in analytics and machine learning tools, Firebase supports feature-rich, data-driven Android apps.
- `Realtime Database`: Cloud-hosted NoSQL database that syncs data in real-time across connected clients. It supports offline access, allowing apps to work smoothly even without network connectivity. Ideal for collaborative and dynamic applications, it automatically handles data syncing and updates as users connect or disconnect.
- `LiveData`: Android Architecture Component that allows UI components to observe data changes while respecting their lifecycle, preventing memory leaks and crashes. It updates observers automatically, simplifying reactive UI patterns in MVVM architecture. MutableLiveData enables data updates, while LiveData ensures read-only access from the UI.
- `DaggerHilt`: Simplifies dependency injection by integrating Dagger with the Android components' lifecycle. Hilt automates the creation and management of dependencies, making configuration easier and improving development efficiency in Android.
- `Navigation`: Simplifies in-app navigation, managing fragment and activity transitions, and handling complex navigation flows. It enables safe argument passing between destinations and offers features like deep linking and back stack management. Designed for modularity, Navigation integrates seamlessly with ViewModel and LiveData in an MVVM architecture.


## Screenshots
<p align="center">
⚠️ Fow now, only HomeScreen has the "Clash Royale" UI style.
</p>
<p align="center">
    <img src="/assets/HomeScreenTicTacToe.png" alt="Home screen" width="200"/>
    <img src="/assets/GameScreenTicTacToe.png" alt="Game screen" width="200"/>
</p>


## Contributing

You can help the project by reporting bugs and making suggestions [here](https://github.com/CarlosCGA/tictactoe/issues).
