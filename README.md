# Phoze — Liquid Glass

Phoze is an iOS-inspired interface prototype packaged as an Android app. It is not a replacement for the Android operating system or a real iPhone operating system.

## Android app
- Native Android launcher activity with a local WebView, so the interface is bundled and opens offline.
- Black startup screen with a white Apple logo for **2 seconds**, followed by a short fade into Phoze.
- iOS-style rounded-square icons, Liquid Glass effects, lock screen and two-panel hinge unlock animation.
- Android launcher icon and portrait-first app layout.
- Minimum Android version: Android 6.0 (API 23).

The boot duration is set in `app/src/main/java/com/phoze/app/MainActivity.kt` as `bootDurationMs = 2000L`.

## Build an APK
The GitHub Actions workflow builds a debug APK whenever code is pushed to `main`, and can also be run manually:
1. Open the repository's **Actions** tab.
2. Select **Build Phoze Android APK**.
3. Select **Run workflow** (or open the latest run triggered by a push).
4. When it succeeds, download the **Phoze-Android-debug** artifact.
5. Extract the ZIP to get `app-debug.apk`, then install it on your Android device.

To build locally, open this repository in Android Studio with Android SDK 35 and JDK 17 installed, then run the Gradle task `assembleDebug`.

## Web prototype
The original browser version remains at `index.html`. The Android app uses its bundled copy at `app/src/main/assets/index.html`. If you change the interface, copy the updated web file into the Android assets file as well.

## Customisation and storage
Appearance preferences are saved in browser local storage inside the app's WebView. Live visual effects still use device RAM and graphics hardware; storage is used for saving settings and bundled assets, not as a substitute for working memory or the GPU.

## Current scope
The app previews are interface placeholders. They do not replace Apple's native apps, iOS services, or Android system features.
