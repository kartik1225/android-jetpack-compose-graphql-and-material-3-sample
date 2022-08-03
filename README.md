# Android app build with jetpack compose and graphql
This sample app follows modern android development paradigms and uses jetpack compose and graphql to build an app.

## Technologies used:
- [Jetpack compose](https://developer.android.com/jetpack/compose)
- [Material You (Material 3)](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary)
- [Apollo graphql](https://github.com/apollographql/apollo-kotlin)
- [View model (Live data)](https://developer.android.com/jetpack/compose/state)
- [Compose destinations library (for navigation)](https://composedestinations.rafaelcosta.xyz/)

## Showcase
It has dark and light mode using material 3 colors (Not dynamic from wallpaper) and this app adapts to system's light/dart mode.

<img src="https://github.com/kartik1225/android-jetpack-compose-graphql-and-material-3-sample/blob/main/assets/home_dark.jpeg" alt="Rick and morty graphql app made with jetpack compose" width="250"/>
<img src="https://github.com/kartik1225/android-jetpack-compose-graphql-and-material-3-sample/blob/main/assets/home_light.jpeg" alt="Rick and morty graphql app made with jetpack compose" width="250"/>
<img src="https://github.com/kartik1225/android-jetpack-compose-graphql-and-material-3-sample/blob/main/assets/characters_dark.jpeg" alt="Rick and morty graphql app made with jetpack compose" width="250"/>
<img src="https://github.com/kartik1225/android-jetpack-compose-graphql-and-material-3-sample/blob/main/assets/characters_light.jpeg" alt="Rick and morty graphql app made with jetpack compose" width="250"/>

### Before you start
Release build will require release keystore, in order to run please create `./gradle.properties` file and then copy the following content to it:

```
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
android.useAndroidX=true
kotlin.code.style=official
android.nonTransitiveRClass=true

RELEASE_STORE_FILE={path to your keystore}
RELEASE_STORE_PASSWORD=*****
RELEASE_KEY_ALIAS=*****
RELEASE_KEY_PASSWORD=*****
```


### Special thanks to:
- [The Rick and Morty API](https://rickandmortyapi.com/) for providing graphql endpoints. 

