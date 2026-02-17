# SYS LTD Movies - Android Native App

A native Android application that integrates with a Flutter module to display movies from TMDB API. This is the Android native part of a cross-platform solution demonstrating native-Flutter integration.

## 📱 Project Overview

This Android application serves as the native host for a Flutter module that displays a list of movies. The app demonstrates:

- **Native-Flutter Integration**: Seamless communication between native Android code and Flutter module using Method Channels
- **TMDB API Integration**: Fetches movie data and trailers from The Movie Database API
- **Modern Android Architecture**: Built with Jetpack Compose, MVVM pattern, Hilt for dependency injection
- **Movie Trailer Playback**: Native screen for displaying YouTube movie trailers via WebView

## 🏗️ Architecture

The project follows Clean Architecture principles with the following layers:

```
app/
├── data/
│   ├── remote/          # API services and DTOs
│   └── repository/      # Repository implementations
├── domain/
│   ├── model/           # Domain models
│   ├── repository/      # Repository interfaces
│   └── usecase/         # Business logic use cases
├── presentation/
│   ├── main/            # Main screen with "Show Movies" button
│   └── trailer/         # Movie trailer screen
├── di/                  # Hilt dependency injection modules
└── ui/theme/            # Compose theme and colors
```

## 🔧 Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Dependency Injection**: Hilt
- **Networking**: Retrofit + OkHttp
- **Navigation**: Jetpack Navigation Compose
- **Flutter Integration**: Flutter Engine + Method Channels
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 36

## 📋 Features

1. **Main Screen**: Displays a "Show List of Movies" button that launches the Flutter module
2. **Flutter Module Integration**: 
   - Launches Flutter module to display movie list
   - Receives movie selection events via Method Channel
3. **Trailer Screen**: 
   - Native screen displaying movie trailers
   - Fetches trailer data from TMDB API
   - Plays YouTube trailers in a WebView

## 🚀 Prerequisites

Before running this project, ensure you have:

1. **Android Studio** (Arctic Fox or later recommended)
2. **JDK 11** or higher
3. **Flutter SDK** installed and configured
4. **Flutter Module** built and available at `../sys_ltd_movies_flutter`

## 📦 Installation & Setup

### Step 1: Clone the Repositories

Clone all three repositories in the same parent directory:

```bash
# Clone Android app
git clone https://github.com/melshenawyarabapps/sys_ltd_movies_android.git

# Clone Flutter module (must be sibling to Android project)
git clone https://github.com/melshenawyarabapps/sys_ltd_movies_flutter.git

# Your directory structure should look like:
# parent_folder/
# ├── sys_ltd_movies_android/
# └── sys_ltd_movies_flutter/
```

### Step 2: Build the Flutter Module

The Flutter module must be built first and its AAR artifacts must be available.

```bash
# Navigate to the Flutter module directory
cd sys_ltd_movies_flutter

# Build the Flutter module as AAR
flutter build aar
```

This will generate the AAR files at `sys_ltd_movies_flutter/build/host/outputs/repo`

### Step 3: Configure TMDB API Key

The app requires a TMDB API key to fetch movie data and trailers. 

#### Option A: Use the provided API key
The project already includes an API key configured in `app/build.gradle.kts`:
```kotlin
buildConfigField("String", "TMDB_API_KEY", "\"2a81b0f3fbbe3656bd9be040bdeed583\"")
```

#### Option B: Use your own API key
1. Create an account at [TMDB](https://www.themoviedb.org/)
2. Go to [API Settings](https://www.themoviedb.org/settings/api) and generate an API key
3. Open `app/build.gradle.kts` and update the API key:

```kotlin
android {
    defaultConfig {
        // Replace with your own API key
        buildConfigField("String", "TMDB_API_KEY", "\"YOUR_API_KEY_HERE\"")
    }
}
```

4. Sync the project with Gradle files

### Step 4: Open and Build in Android Studio

1. Open Android Studio
2. Select `File > Open` and navigate to `sys_ltd_movies_android`
3. Wait for Gradle sync to complete
4. Build the project: `Build > Make Project`

### Step 5: Run the App

1. Connect an Android device or start an emulator
2. Click `Run > Run 'app'` or press `Shift + F10`

## 🔌 Flutter Integration

### Method Channel Communication

The app communicates with the Flutter module via Method Channels:

**Channel Name**: `com.movies.movies_flutter/channel`

**Methods**:
- `onMovieTap(movieId: Int)`: Called by Flutter when a user selects a movie

### FlutterBridge

The `FlutterBridge` object manages:
- Flutter Engine initialization and caching
- Method Channel configuration
- FlutterFragment creation

```kotlin
// Initialize Flutter Engine (in Application class)
FlutterBridge.init(context)

// Launch Flutter module
val flutterFragment = FlutterBridge.createFlutterFragment()

// Configure method channel callbacks
FlutterBridge.configureChannel(engine) { movieId ->
    // Handle movie selection
}
```

## 📁 Project Structure

```
sys_ltd_movies_android/
├── app/
│   ├── src/main/
│   │   ├── java/com/movies/sys_ltd_movies_android/
│   │   │   ├── FlutterBridge.kt        # Flutter integration
│   │   │   ├── MainActivity.kt         # Main activity
│   │   │   ├── MoviesApplication.kt    # Application class
│   │   │   ├── data/                   # Data layer
│   │   │   ├── di/                     # DI modules
│   │   │   ├── domain/                 # Domain layer
│   │   │   ├── presentation/           # UI layer
│   │   │   └── ui/theme/               # Compose theme
│   │   ├── res/                        # Resources
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml              # Version catalog
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## 🔗 Related Repositories

This project is part of a three-repository solution:

1. **Android Native App** (this repository): [sys_ltd_movies_android](https://github.com/melshenawyarabapps/sys_ltd_movies_android.git)
2. **Flutter Module**: [sys_ltd_movies_flutter](https://github.com/melshenawyarabapps/sys_ltd_movies_flutter.git) - Movie list display with Bloc state management
3. **iOS Native App**: [sysLtdMoviesIos](https://github.com/melshenawyarabapps/sysLtdMoviesIos.git) - iOS host application

## 👨‍💻 Author

Mohamed Elshenawy


