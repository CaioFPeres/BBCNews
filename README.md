# BBC News Project

## Overview
This project aims to create an app for displaying news in a friendly way for users.
At the moment, it just makes a call for the API and render the whole News object on the screen.

This project is using Retrofit2 as the HTTP client from which we would do
requests to the news API, Koin as dependency injector, Kotlin, Jetpack Compose, MVVM (Model-View-ViewModel) and Clean Architecture.

In order for anyone to run this without any problems, the API key is included in the code source.
But it is wide known that this is not ideal since anyone can publicly see it.
Ideally, it should be at gradle.properties as a property, and retrieved at runtime using BuildConfig.

## Clean Architecture Folder Structure

``` bash
📂 BBCNews
├── MainActivity.kt
├── 📂 ui (View Layer)
│   ├── 📂 biometricsScreen
│   │   ├── BiometricsScreen.kt
│   │   ├── BiometricsState.kt
│   │   ├── BiometricsViewModel.kt
│   │   │
│   ├── 📂 mainScreen
│   │   ├── MainScreen.kt
│   │   ├── MainScreenViewModel.kt
│   │   │
│   ├── 📂 newsScreen
│   │   ├── MainScreen.kt
│   │   ├── MainScreenViewModel.kt
│   │   ├── NewsUiState.kt

├── 📂 domain (Business Logic Layer)
│   ├── 📂 model
│   │   ├── News.kt
│   ├── 📂 repository
│   │   ├── NewsRepository.kt
│   ├── 📂 usecase
│   │   ├── AuthenticateUseCase.kt
│   │   ├── GetSortedUseCase.kt
│   │   ├── ReplaceContentWithUrlUseCase.kt

├── 📂 data (Data Layer)
│   ├── 📂 remote
│   │   ├── NewsAPI.kt
│   │   ├── RetrofitClient.kt
│   ├── 📂 repository
│   │   ├── NewsRepositoryImpl.kt

├── 📂 di (Dependency Injection)
│   ├── App.kt
│   ├── modules.kt
```

## Unit Tests
- For some reason, MainScreenViewModelTest is not running with the rest of the tests. Need to run individually.
- Some functions could not be tested due to their nature.

## Executing the project
- Open on Android Studio and press Run.