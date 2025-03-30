# BBC News Project

## Overview
This project aims to create an app for displaying news in a friendly way for users.
At the moment, it just makes a call for the API and render the whole News object on the screen.

Next steps:
 - Display using cards for better UI experience.

This project is using Retrofit2 as the HTTP client from which we would do
requests to the news API, Kotlin, Jetpack Compose and MVVM as design pattern.
In order for anyone to run this without any problems, the API key is included in the code source.
But it is wide known that this is not the best way since anyone can publicly see it.
Ideally, it should be at gradle.properties as a property, and retrieved at runtime using BuildConfig.

## Clean Architecture Folder Structure

``` bash
📂 BBCNews
├── MainActivity.kt
├── 📂 ui (View Layer)
│   ├── 📂 mainScreen
│   │   ├── MainScreen.kt
│   │   ├── MainScreenViewModel.kt
│   │   ├── NewsUiState.kt

├── 📂 domain (Business Logic Layer)
│   ├── 📂 model
│   │   ├── News.kt
│   ├── 📂 repository
│   │   ├── NewsRepository.kt

├── 📂 data (Data Layer)
│   ├── 📂 remote
│   │   ├── NewsAPI.kt
│   │   ├── RetrofitClient.kt
│   ├── 📂 repository
│   │   ├── NewsRepositoryImpl.kt
```

## Executing the project
- Open on Android Studio and press Run.